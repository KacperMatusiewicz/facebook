package ripoff.facebook.notification.notification.details.notificationType.notificationChannel;

import org.springframework.stereotype.Component;
import ripoff.facebook.notification.commons.NotificationMethod;
import ripoff.facebook.notification.notification.Notification;

@Component
public class NotificationFactory {
    public Notification createNotification(NotificationMethod channel, String destination) {
        switch (channel) {
            case SMS:
                Notification notification = new TwilioNotification();
                notification.setDestination(destination);
                return notification;
            case MAIL:
                Notification notification1 = new MailNotification();
                notification1.setDestination(destination);
                return notification1;
            case WEB_APP:
                Notification notification2 = new AppNotification();
                notification2.setDestination(destination);
                return notification2;
            case MOBILE_APP:
                Notification notification3 = new MobileAppNotification();
                notification3.setDestination(destination);
                return notification3;
            default:
                throw new IllegalArgumentException("Unknown channel");
        }
    }
}
