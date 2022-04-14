package ripoff.facebook.notification.notification.sendNotification.application;

import ripoff.facebook.notification.notification.sendNotification.application.details.NotificationParameters;

public interface Notification {

    void notify(NotificationParameters parameters);

    void setDestination(String destination);
}
