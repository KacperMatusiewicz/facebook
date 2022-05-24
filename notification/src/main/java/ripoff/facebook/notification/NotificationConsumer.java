package ripoff.facebook.notification;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ripoff.facebook.amqp.NotificationDTO;

@Component
@AllArgsConstructor
public class NotificationConsumer {

    NotificationService service;

    @RabbitListener(queues ="general-notification-queue", containerFactory = "generalContainerFactory")
    public void listen(NotificationDTO message) {
        System.out.println(message.getContent() + " " + message.getNotificationType().toString() + " " + message.getUserId());
        service.sendNotification(message);
    }
}
