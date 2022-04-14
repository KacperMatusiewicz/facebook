package ripoff.facebook.notification.notification.sendNotification.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.notification.notification.sendNotification.application.details.exception.UnsupportedNotificationType;
import ripoff.facebook.notification.notification.sendNotification.application.details.notificationType.AllNotification;
import ripoff.facebook.notification.notification.sendNotification.application.details.notificationType.ExternalNotification;
import ripoff.facebook.notification.notification.sendNotification.application.details.notificationType.InternalNotification;
import ripoff.facebook.notification.notification.sendNotification.application.details.notificationType.NotificationType;
import ripoff.facebook.notification.preference.NotificationPreferenceService;


@Service
@AllArgsConstructor
public class NotificationService {

    NotificationPreferenceService preferenceService;

    public void sendNotification(NotificationDTO notificationDTO) {
        NotificationTemplate notificationTemplate = setNotificationTemplate(notificationDTO.getNotificationParameters().getNotificationType());
        notificationTemplate.executeNotification(notificationDTO);
    }

    private NotificationTemplate setNotificationTemplate(NotificationType notificationType) {
        switch (notificationType) {
            case ALL_LEVEL:
                return new AllNotification(preferenceService);
            case INTERNAL_LEVEL:
                return new InternalNotification(preferenceService);
            case EXTERNAL_LEVEL:
                return new ExternalNotification(preferenceService);
            default:
                throw new UnsupportedNotificationType("Unsupported notification type.");
        }
    }

}