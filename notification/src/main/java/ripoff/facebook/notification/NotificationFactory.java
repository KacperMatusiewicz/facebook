package ripoff.facebook.notification;

import org.springframework.stereotype.Component;
import ripoff.facebook.notification.preferences.NotificationMethod;

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
            default:
                throw new IllegalArgumentException("Unknown channel");
        }
    }
}
