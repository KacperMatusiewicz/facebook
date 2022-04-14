package ripoff.facebook.notification.notification.sendNotification.controller;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ripoff.facebook.notification.notification.sendNotification.application.NotificationDTO;
import ripoff.facebook.notification.notification.sendNotification.application.NotificationService;

@Component
@AllArgsConstructor
public class NotificationConsumer {

    NotificationService service;

    @RabbitListener(queues ="general-notification-queue", containerFactory = "generalContainerFactory")
    public void listen(NotificationDTO message) {
        service.sendNotification(message);
    }
}
