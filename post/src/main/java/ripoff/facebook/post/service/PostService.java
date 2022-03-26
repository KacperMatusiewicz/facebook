package ripoff.facebook.post.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import ripoff.facebook.amqp.FeedPostInformation;
import ripoff.facebook.post.*;
import ripoff.facebook.post.dto.PostCreationRequest;
import ripoff.facebook.post.dto.VisibilityGroupType;
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
    AmqpTemplate template;

    public void createPost(PostCreationRequest request){

        Set<Long> visibilityUserIds;

        if(!postDataValidationService.validatePost(request)){
            throw new BadPostDataException("Post data is not correct.");
        }

        Post post = Post.builder()
                .userId(request.getUserId())
                .content(request.getContent())
                .creationDate(timeService.getCurrentDateTime())
                .attachmentPath(null)
                .build();

        Long postId = repository.save(post).getId();

        if(request.getVisibilityGroupId() == null && request.getVisibilityUsersId() == null) {
            throw new BadPostDataException("Recipient group not defined");
        }

        if (request.getVisibilityGroupType()== VisibilityGroupType.FOLLOWERS) {
            visibilityUserIds = relationService.getFollowers(request.getVisibilityGroupId());
        } else {
            visibilityUserIds = request.getVisibilityUsersId();
        }

        visibilityUserIds.forEach(
                (userId) -> template.convertAndSend(
                        "general-feed-queue",
                        new FeedPostInformation(userId, postId)
                )
        );

    }

    public List<Post> getAllPostsByUser(Long userId) {
        return repository.findAllByUserId(userId);
    }

}
