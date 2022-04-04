package ripoff.facebook.amqp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NotificationInformation {
    Long userId;
    NotificationParameters notificationParameters;

}
