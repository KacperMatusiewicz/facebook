package ripoff.facebook.post.createPost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import ripoff.facebook.amqp.FeedPostInformation;
import ripoff.facebook.post.commons.TimeService;
import ripoff.facebook.post.commons.repository.Post;
import ripoff.facebook.post.commons.repository.PostRepository;
import ripoff.facebook.post.createPost.service.dto.PostCreationDto;
import ripoff.facebook.post.createPost.service.dto.VisibilityGroupType;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final PostRepository repository;
    private final PostDataValidationService dataValidationService;
    private final RelationService relationService;
    private final TimeService timeService;
    private final AmqpTemplate template;

    public void createPost(PostCreationDto request) {
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

    private Set<Long> getVisibilityUserIds(PostCreationDto request) {
        Set<Long> visibilityUserIds;
        switch(request.getVisibilityGroupType()) {
            case FOLLOWERS:
                visibilityUserIds = relationService.getFollowers(request.getUserId());
                break;
            case FRIENDS:
                visibilityUserIds = relationService.getFriends(request.getUserId());
                break;
            case CUSTOM:
                visibilityUserIds = request.getVisibilityUsersId();
                break;
            default:
                throw new BadPostDataException("Couldn't resolve post visibility user group.");

        }
        return visibilityUserIds;
    }

    private Long savePost(PostCreationDto request) {
        Post post = Post.builder()
                .userId(request.getUserId())
                .content(request.getContent())
                .creationDate(timeService.getCurrentDateTime())
                .attachmentPath(null)
                .build();
        return repository.save(post).getId();
    }

    private void validatePost(PostCreationDto request) {
        if (!dataValidationService.validatePost(request)) {
            throw new BadPostDataException("Post data is not correct.");
        }
        if (request.getVisibilityUsersId() == null) {
            throw new BadPostDataException("Recipient group not defined");
        }
    }
}
