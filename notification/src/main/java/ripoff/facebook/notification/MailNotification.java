package ripoff.facebook.notification;

import ripoff.facebook.notification.dto.NotificationParameters;

public class MailNotification implements Notification{

    private String email;

    @Override
    public void notify(NotificationParameters parameters) {
        System.out.println("sending mail to address: " + email + "and the content is:" + parameters.getText());
    }

    @Override
    public void setDestination(String destination) {
        this.email = destination;
    }
}
