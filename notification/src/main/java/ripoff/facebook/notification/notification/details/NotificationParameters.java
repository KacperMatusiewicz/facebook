package ripoff.facebook.notification.notification.details;

import lombok.Data;
import ripoff.facebook.notification.notification.details.notificationType.NotificationType;

@Data
public class NotificationParameters {
    private NotificationType notificationType;
    private String text;

}
