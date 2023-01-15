package ripoff.facebook.amqp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NotificationDTO implements Serializable {
    private Long userId;
    private String content;
    private Long relatedId;
    private NotificationType notificationType;
}
