package ripoff.facebook.notification.notification.sendNotification.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ripoff.facebook.notification.notification.sendNotification.application.details.NotificationParameters;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NotificationDTO {
    Long userId;
    NotificationParameters notificationParameters;

}
