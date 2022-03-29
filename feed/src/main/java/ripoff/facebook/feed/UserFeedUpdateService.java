package ripoff.facebook.feed;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.connection.ConnectionFactoryContextWrapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserFeedUpdateService {

    private ConnectionFactoryContextWrapper wrapper;
    private RabbitTemplate template;

    public void addPostToUserQueue(Long userId, Long postId) {
        wrapper.run("user", () -> template.convertAndSend(Long.toString(userId), new FeedPostEntry(postId)));
    }
}
