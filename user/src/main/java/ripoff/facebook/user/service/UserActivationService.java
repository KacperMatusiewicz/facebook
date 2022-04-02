package ripoff.facebook.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.feed.FeedUserClient;
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
    FeedUserClient feedUserClient;
    RelationClient relationClient;

    public void activateUserAccount(Long activationKey) {

        if(activationRepository.existsById(activationKey)) {
            User user = activationRepository.getById(activationKey).getUser();
            user.setUserStatus(UserStatus.ACTIVE);
            relationClient.createUser(user.getId());
            feedUserClient.createUserQueue(user.getId());
            userRepository.save(user);
        } else {
            throw new ActivationLinkNotFound("Activation key does not exist.");
        }
    }
}
