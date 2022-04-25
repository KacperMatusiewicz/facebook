package ripoff.facebook.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.authentication.AuthClient;
import ripoff.facebook.clients.authentication.AuthenticationMethodDto;
import ripoff.facebook.clients.notification.UserNotificationQueueClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.user.entity.User;
import ripoff.facebook.user.entity.UserStatus;
import ripoff.facebook.user.exceptions.ActivationLinkNotFound;
import ripoff.facebook.user.repository.ActivationRepository;
import ripoff.facebook.user.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserActivationService {

    ActivationRepository activationRepository;
    UserRepository userRepository;
    UserNotificationQueueClient userNotificationQueueClient;
    RelationClient relationClient;

    AuthClient authClient;

    public void activateUserAccount(Long activationKey) {
        if (activationLinkExists(activationKey)) {
            User user = changeUserAccountStatusToActive(activationKey);
            initializeUser(user);
        } else {
            throw new ActivationLinkNotFound("Activation key does not exist.");
        }
    }

    private void initializeUser(User user) {
        authClient.registerNewUserAuthenticationMethod(new AuthenticationMethodDto(user.getId(), user.getEmail(), user.getPassword()));
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
