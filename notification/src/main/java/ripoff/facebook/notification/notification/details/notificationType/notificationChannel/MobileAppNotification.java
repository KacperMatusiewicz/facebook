package ripoff.facebook.notification.notification.details.notificationType.notificationChannel;

import ripoff.facebook.notification.notification.Notification;
import ripoff.facebook.notification.notification.details.NotificationParameters;

public class MobileAppNotification implements Notification {
    @Override
    public void notify(NotificationParameters parameters) {
        System.out.println("mobile app notification");
    }

    @Override
    public void setDestination(String destination) {

    }
}
