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

    public PostIdsList getFeedPosts(Long userId, int amount) {
        List<Long> postIds = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            try {
                postIds.add(getFeedPost(userId).getPostId());
            } catch (FeedEmptyException e) {
                if(postIds.isEmpty()){
                    throw new FeedEmptyException("Feed is empty");
                } else {
                    return new PostIdsList(postIds);
                }
            }
        }
        return new PostIdsList(postIds);
    }

    private FeedPostEntry getFeedPost(Long userId) {
        Optional<Message> message = Optional.ofNullable(wrapper.call("user", () -> template.receive(Long.toString(userId))));
        return (FeedPostEntry) userMessageConverter.fromMessage(message.orElseThrow(() -> new FeedEmptyException("Feed is empty")));
    }
}
