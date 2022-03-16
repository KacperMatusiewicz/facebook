package ripoff.facebook.post;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository repository;
    private TimeService timeService;
    private PostDataValidationService postDataValidationService;

    public void createPost(PostCreationRequest request){

        if(!postDataValidationService.validatePost(request)){
            throw new BadPostDataException("Post data is not correct.");
        }

        Post post = Post.builder()
                .userId(request.getUserId())
                .content(request.getContent())
                .creationDate(timeService.getCurrentDateTime())
                .attachmentPath(null)
                .build();

        repository.save(post);
    }

    public List<Post> getAllPostsByUser(Long userId) {
        return repository.findAllByUserId(userId);
    }
}
