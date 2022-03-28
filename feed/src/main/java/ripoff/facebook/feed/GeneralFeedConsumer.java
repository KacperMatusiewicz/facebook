package ripoff.facebook.feed;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ripoff.facebook.amqp.FeedPostInformation;

@Component
public class GeneralFeedConsumer {
    @RabbitListener(queues ="general-feed-queue", containerFactory = "generalContainerFactory")
    public void listen(FeedPostInformation message) {
        System.out.println(message.getPostId() + " " + message.getUserId());
    }
}
