package ripoff.facebook.user.deleteUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.authentication.AuthClient;
import ripoff.facebook.clients.notification.UserNotificationQueueClient;
import ripoff.facebook.clients.post.PostClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.clients.search.SearchClient;
import ripoff.facebook.user.commons.UserRepository;

@Service
@RequiredArgsConstructor
public class DeleteUserService {
    private final RelationClient relationClient;
    private final PostClient postClient;
    private final AuthClient authenticationClient;

    private final SearchClient searchClient;
    private final UserRepository userRepository;

    public void deleteUser(Long userId) {
        //TODO: Saga
        relationClient.deleteUser(userId);
        postClient.deleteAllPostsByUserId(userId);
        authenticationClient.deleteUserAuthenticationData(userId);
        searchClient.removeUser(userId);
        userRepository.deleteById(userId);
    }
}
