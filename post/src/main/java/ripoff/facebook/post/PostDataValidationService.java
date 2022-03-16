package ripoff.facebook.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Getter
@AllArgsConstructor
public class PostDataValidationService {

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
        Optional<Boolean> response = Optional.ofNullable(
                restTemplate.getForObject("localhost:8080/api/v1/user/"+userId, Boolean.class)
        );
        return response.orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Could not connect to the User service"
                )
        );
    }

}
