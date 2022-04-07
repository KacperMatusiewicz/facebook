package ripoff.facebook.feed;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ripoff.facebook.amqp.FeedPostInformation;

@Component
@AllArgsConstructor
public class GeneralFeedConsumer {

    UserFeedService service;

    @RabbitListener(queues ="general-feed-queue", containerFactory = "generalContainerFactory")
    public void listen(FeedPostInformation message) {
        System.out.println(message);
        service.addNewUserFeedPost(message);
    }
}
