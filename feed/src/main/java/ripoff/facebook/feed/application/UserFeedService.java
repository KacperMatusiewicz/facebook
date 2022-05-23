package ripoff.facebook.feed.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.amqp.FeedPostInformation;
import ripoff.facebook.feed.repository.UserFeedPostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFeedService {

    private final UserFeedPostRepository repository;

    public void addNewUserFeedPost(FeedPostInformation feedPostInformation) {
        repository.addToBeginningOfUserFeed(feedPostInformation.getUserId(), feedPostInformation.getPostId());
    }

    public List<String> getAllPostsByUserId(Long userId) {
        return repository.findAllByUserId(userId)
                .orElseThrow(
                        () -> new FeedEmptyException("Feed posts found for user id: "+ userId + " not found.")
                );
    }
}
