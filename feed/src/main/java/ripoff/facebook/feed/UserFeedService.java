package ripoff.facebook.feed;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.amqp.FeedPostInformation;

import java.util.ArrayList;
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
        //List<UserFeedPost> posts = new ArrayList<>();
        //repository.findAllByUserId(userId).forEach(userFeedPost -> posts.add(userFeedPost));
        //return posts;
        List<UserFeedPost> posts = repository.findAllByUserId(userId);
        repository.findAll().forEach(System.out::println);
        System.out.println(posts);
        return posts;
    }
}
