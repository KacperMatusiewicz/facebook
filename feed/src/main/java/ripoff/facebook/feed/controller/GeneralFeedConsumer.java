package ripoff.facebook.feed.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ripoff.facebook.amqp.FeedPostInformation;
import ripoff.facebook.feed.application.UserFeedService;
@Slf4j
@Component
@AllArgsConstructor
public class GeneralFeedConsumer {

    UserFeedService service;

    @RabbitListener(queues ="general-feed-queue", containerFactory = "generalContainerFactory")
    public void pullFeedRequest(FeedPostInformation message) {
        log.info("Received new feed post.");
        service.addNewUserFeedPost(message);
    }
}
