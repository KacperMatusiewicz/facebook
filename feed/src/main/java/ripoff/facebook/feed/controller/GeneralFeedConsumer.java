package ripoff.facebook.feed.controller;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ripoff.facebook.amqp.FeedPostInformation;
import ripoff.facebook.feed.application.UserFeedService;

@Component
@AllArgsConstructor
public class GeneralFeedConsumer {

    UserFeedService service;

    @RabbitListener(queues ="general-feed-queue", containerFactory = "generalContainerFactory")
    public void pullFeedRequest(FeedPostInformation message) {
        service.addNewUserFeedPost(message);
    }
}
