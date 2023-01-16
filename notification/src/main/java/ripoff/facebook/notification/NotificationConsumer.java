package ripoff.facebook.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ripoff.facebook.amqp.NotificationDTO;
@Slf4j
@Component
@AllArgsConstructor
public class NotificationConsumer {

    NotificationService service;

    @RabbitListener(queues ="general-notification-queue", containerFactory = "generalContainerFactory")
    public void listen(NotificationDTO message) {
        log.info(
                "Received notification with related id: " + message.getRelatedId() +
                " and user id: " + message.getUserId()
        );
        service.sendNotification(message);
    }
}
