package ripoff.facebook.post.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Qualifier("general-feed-template")
    AmqpTemplate template;

    public void createPost(PostCreationRequest request){
        validatePost(request);
        Long postId = savePost(request);
        Set<Long> visibilityUserIds = getVisibilityUserIds(request);
        visibilityUserIds.forEach(
                userId -> template.convertAndSend(
                        "general-feed-queue",
                        new FeedPostInformation(userId, postId)
                )
        );
    }

    private Set<Long> getVisibilityUserIds(PostCreationRequest request) {
        Set<Long> visibilityUserIds;
        if (request.getVisibilityGroupType()== VisibilityGroupType.FOLLOWERS) {
            visibilityUserIds = relationService.getFollowers(request.getVisibilityGroupId());
        } else {
            visibilityUserIds = request.getVisibilityUsersId();
        }
        return visibilityUserIds;
    }

    private Long savePost(PostCreationRequest request) {
        Post post = Post.builder()
                .userId(request.getUserId())
                .content(request.getContent())
                .creationDate(timeService.getCurrentDateTime())
                .attachmentPath(null)
                .build();

        Long postId = repository.save(post).getId();
        return postId;
    }

    private void validatePost(PostCreationRequest request) {
        if(!postDataValidationService.validatePost(request)){
            throw new BadPostDataException("Post data is not correct.");
        }
        if(request.getVisibilityGroupId() == null && request.getVisibilityUsersId() == null) {
            throw new BadPostDataException("Recipient group not defined");
        }
    }

    public List<Post> getAllPostsByUser(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Transactional
    public void deleteAllPostsByUserId(Long userId) {
        repository.deleteAllByUserId(userId);
    }

}
