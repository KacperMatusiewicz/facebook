package ripoff.facebook.notification.notification.sendNotification.application.details.notificationType.notificationChannel;

import ripoff.facebook.notification.notification.sendNotification.application.Notification;
import ripoff.facebook.notification.notification.sendNotification.application.details.NotificationParameters;

public class TwilioNotification implements Notification {

    private String phoneNumber;

    @Override
    public void notify(NotificationParameters parameters) {
        System.out.println("sending twilio notification to number: " + phoneNumber + " and the content is: " + parameters.getText());
    }

    @Override
    public void setDestination(String destination) {
        this.phoneNumber = destination;
    }
}