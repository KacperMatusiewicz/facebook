package ripoff.facebook.notification.dto;

import lombok.Data;

@Data
public class SendNotificationDTO {

    Long userId;
    NotificationParameters parameters;


}
