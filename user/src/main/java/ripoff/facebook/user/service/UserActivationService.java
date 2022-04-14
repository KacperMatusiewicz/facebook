package ripoff.facebook.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.notification.UserNotificationQueueClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.user.entity.User;
import ripoff.facebook.user.entity.UserStatus;
import ripoff.facebook.user.exceptions.ActivationLinkNotFound;
import ripoff.facebook.user.repository.ActivationRepository;
import ripoff.facebook.user.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserActivationService {

    ActivationRepository activationRepository;
    UserRepository userRepository;
    UserNotificationQueueClient userNotificationQueueClient;
    RelationClient relationClient;

    public void activateUserAccount(Long activationKey) {
        if (activationLinkExists(activationKey)) {
            User user = changeUserAccountStatusToActive(activationKey);
            initializeUser(user);
        } else {
            throw new ActivationLinkNotFound("Activation key does not exist.");
        }
    }

    private void initializeUser(User user) {
        relationClient.createUser(user.getId());
        userNotificationQueueClient.createQueue(user.getId());
    }

    private User changeUserAccountStatusToActive(Long activationKey) {
        User user = activationRepository.getById(activationKey).getUser();
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        return user;
    }

    private boolean activationLinkExists(Long activationKey) {
        return activationRepository.existsById(activationKey);
    }
}
