package ripoff.facebook.user.activateAccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.authentication.AuthClient;
import ripoff.facebook.clients.authentication.AuthenticationMethodDto;
import ripoff.facebook.clients.notification.UserNotificationQueueClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.clients.search.UserRequest;
import ripoff.facebook.clients.search.SearchClient;
import ripoff.facebook.user.commons.*;

@Service
@RequiredArgsConstructor
public class AccountActivationService {

    private final ActivationRepository activationRepository;
    private final UserRepository userRepository;
    private final RelationClient relationClient;
    private final AuthClient authClient;
    private final SearchClient searchClient;

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
        ActivationLink activationLink = activationRepository.getById(activationKey);
        User user = activationLink.getUser();

        activationLink.setUsed(true);
        user.setUserStatus(UserStatus.ACTIVE);

        userRepository.save(user);
        activationRepository.save(activationLink);

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
        searchClient.addUser(new UserRequest(user.getId(), user.getName(), user.getLastName()));
    }
}
