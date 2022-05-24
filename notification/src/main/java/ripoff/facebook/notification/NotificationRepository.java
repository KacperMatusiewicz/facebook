package ripoff.facebook.notification;

import ripoff.facebook.amqp.NotificationDTO;

import java.util.List;

public interface NotificationRepository {
    void saveNotification(NotificationDTO notificationDTO);

    List<NotificationDTO> getAllUnreadNotificationsByUserId(Long userId);

    void deleteByRelationId(NotificationDTO notificationDTO);
}
