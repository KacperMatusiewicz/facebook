package ripoff.facebook.notification.entity;

import ripoff.facebook.amqp.NotificationParameters;

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
