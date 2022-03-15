package ripoff.facebook.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.post.exceptions.BadPostDataException;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository repository;
    private RelationService relationService;
    private TimeService timeService;
    private PostDataValidationService postDataValidationService;

    public void createPost(PostCreationRequest request){
        Set<Long> visibilityUserId;

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
        if (request.getVisibilityGroupId() != null) {
            visibilityUserId = relationService.getGroupIds(request.getVisibilityGroupId());
        } else {
            visibilityUserId = request.getVisibilityUsersId();
        }
        //exception jesli obydwa sa nullem
        //dodac do kolejki

    }

    public List<Post> getAllPostsByUser(Long userId) {
        return repository.findAllByUserId(userId);
    }
}
