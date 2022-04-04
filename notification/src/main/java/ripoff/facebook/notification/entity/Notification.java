package ripoff.facebook.notification.entity;

import ripoff.facebook.amqp.NotificationParameters;

public interface Notification {
    void notify(NotificationParameters parameters);

    void setDestination(String destination);
}
