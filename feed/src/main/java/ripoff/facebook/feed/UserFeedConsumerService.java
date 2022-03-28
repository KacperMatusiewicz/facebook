package ripoff.facebook.feed;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactoryContextWrapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserFeedConsumerService {

    private RabbitTemplate template;
    private ConnectionFactoryContextWrapper wrapper;
    private MessageConverter userMessageConverter;

    public PostIdsList getFeedPosts(Long userId) {
        List<Long> postIds = new ArrayList<>();
        wrapper.run(
            "user",
            () -> {
                FeedPostEntry feedPostEntry =
                        (FeedPostEntry) userMessageConverter.fromMessage(
                                Optional.ofNullable(
                                            template.receive(Long.toString(userId)))
                                                    .orElseThrow(() -> new FeedEmptyException("Feed is empty."))
                        );
                postIds.add(feedPostEntry.getPostId());
            }
        );
        return new PostIdsList(postIds);
    }
}
