package ripoff.facebook.notification.notification;

import ripoff.facebook.notification.notification.details.NotificationParameters;

public interface Notification {

    void notify(NotificationParameters parameters);

    void setDestination(String destination);
}
