package ripoff.facebook.post;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository repository;
    private RelationService relationService;

    public void createPost(PostCreationRequest request){
        Set<Long> visibilityUserId;
        Post post = Post.builder()
                .userId(request.getUserId())
                .content(request.getContent())
                .creationDate(LocalDateTime.now())
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
