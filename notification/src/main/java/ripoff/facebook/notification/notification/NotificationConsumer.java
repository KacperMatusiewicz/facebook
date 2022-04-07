package ripoff.facebook.notification.notification;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationConsumer {

    NotificationService service;

    @RabbitListener(queues ="general-notification-queue", containerFactory = "generalContainerFactory")
    public void listen(NotificationDTO message) {
        service.sendNotification(message);
    }
}
