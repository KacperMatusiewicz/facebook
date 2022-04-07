package ripoff.facebook.notification.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ripoff.facebook.notification.notification.details.NotificationParameters;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NotificationDTO {
    Long userId;
    NotificationParameters notificationParameters;

}
