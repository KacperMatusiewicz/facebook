package ripoff.facebook.notification.notification.sendNotification.application.details;

import lombok.Data;
import ripoff.facebook.notification.notification.sendNotification.application.details.notificationType.NotificationType;

@Data
public class NotificationParameters {
    private NotificationType notificationType;
    private String text;

}
