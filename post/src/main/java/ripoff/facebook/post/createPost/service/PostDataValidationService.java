package ripoff.facebook.post.createPost.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ripoff.facebook.clients.user.UserClient;
import ripoff.facebook.post.createPost.service.dto.PostCreationDto;

@Service
@Getter
@AllArgsConstructor
public class PostDataValidationService {

    private final UserClient userClient;
    private final int contentMaxLength = 512;
    private final RestTemplate restTemplate;

    public boolean validatePost(PostCreationDto postCreationRequest) {
        return validateContent(postCreationRequest.getContent())
                && validateUserId(postCreationRequest.getUserId());
    }

    public boolean validateContent(String content) {
        return content.length() <= contentMaxLength;
    }

    public boolean validateUserId(Long userId) {
        System.out.println(userId);
        return userClient.checkIfUserExistsById(userId).getUserExists();
    }

}
