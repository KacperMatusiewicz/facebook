package ripoff.facebook.user.activateAccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.authentication.AuthClient;
import ripoff.facebook.clients.authentication.AuthenticationMethodDto;
import ripoff.facebook.clients.notification.UserNotificationQueueClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.user.commons.ActivationRepository;
import ripoff.facebook.user.commons.User;
import ripoff.facebook.user.commons.UserRepository;
import ripoff.facebook.user.commons.UserStatus;

@Service
@RequiredArgsConstructor
public class AccountActivationService {

    private final ActivationRepository activationRepository;
    private final UserRepository userRepository;
    private final UserNotificationQueueClient userNotificationQueueClient;
    private final RelationClient relationClient;
    private final AuthClient authClient;

    //TODO: SAGA?
    public void activateUserAccount(Long activationKey) {
        if (activationLinkExists(activationKey)) {
            User user = changeUserAccountStatusToActive(activationKey);
            initializeUser(user);
        } else {
            throw new ActivationLinkNotFound("Activation key does not exist.");
        }
    }

    private boolean activationLinkExists(Long activationKey) {
        return activationRepository.existsById(activationKey);
    }

    private User changeUserAccountStatusToActive(Long activationKey) {
        User user = activationRepository.getById(activationKey).getUser();
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        return user;
    }

    private void initializeUser(User user) {
        authClient.registerNewUserAuthenticationMethod(
                new AuthenticationMethodDto(
                        user.getId(),
                        user.getEmail(),
                        user.getPassword()
                )
        );
        relationClient.createUser(user.getId());
        userNotificationQueueClient.createQueue(user.getId());
    }
}
