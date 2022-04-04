package ripoff.facebook.notification.controller;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ripoff.facebook.amqp.FeedPostInformation;
import ripoff.facebook.amqp.NotificationInformation;
import ripoff.facebook.notification.service.NotificationService;

@Component
@AllArgsConstructor
public class NotificationConsumer {

    NotificationService service;

    @RabbitListener(queues ="general-notification-queue", containerFactory = "generalContainerFactory")
    public void listen(NotificationInformation message) {
        service.sendNotification(message);
    }
}
