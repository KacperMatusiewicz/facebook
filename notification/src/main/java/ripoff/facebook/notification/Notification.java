package ripoff.facebook.notification;

import ripoff.facebook.notification.dto.NotificationParameters;

public interface Notification {
    void notify(NotificationParameters parameters);

    void setDestination(String destination);
}
