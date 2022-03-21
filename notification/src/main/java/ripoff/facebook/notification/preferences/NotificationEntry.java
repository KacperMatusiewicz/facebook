package ripoff.facebook.notification.preferences;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NotificationEntry {

    @Id
    private Long id;


    private NotificationMethod notificationMethod;

    private String destination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public NotificationMethod getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(NotificationMethod notificationMethod) {
        this.notificationMethod = notificationMethod;
    }
}
