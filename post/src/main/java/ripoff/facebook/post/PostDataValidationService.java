package ripoff.facebook.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ripoff.facebook.clients.user.UserClient;
import ripoff.facebook.clients.user.UserExistsResponse;

import java.util.Optional;

@Service
@Getter
@AllArgsConstructor
public class PostDataValidationService {

    private final UserClient userClient;
    private final int contentMaxLength = 512;
    private final RestTemplate restTemplate;

    public boolean validatePost(PostCreationRequest postCreationRequest) {
        return validateContent(postCreationRequest.getContent())
                && validateUserId(postCreationRequest.getUserId());
    }

    public boolean validateContent(String content){
        return content.length() <= contentMaxLength;
    }

    public boolean validateUserId(Long userId) {
        return userClient.checkIfUserExistsById(userId).getUserExists();
    }

}
