package ripoff.facebook.feed.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.amqp.FeedPostInformation;
import ripoff.facebook.feed.repository.UserFeedPost;
import ripoff.facebook.feed.repository.UserFeedPostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserFeedService {

    UserFeedPostRepository repository;

    public void addNewUserFeedPost(FeedPostInformation feedPostInformation) {
        repository.save(
                new UserFeedPost(
                        feedPostInformation.getUserId(), feedPostInformation.getPostId()
                )
        );
    }

    public List<UserFeedPost> getAllPostsByUserId(Long userId) {
        List<UserFeedPost> posts = repository
                .findAllByUserId(userId)
                .orElseThrow(
                        () -> new FeedEmptyException("Feed posts for user id: "+ userId + " not found.")
                );
        return posts;
    }
}
