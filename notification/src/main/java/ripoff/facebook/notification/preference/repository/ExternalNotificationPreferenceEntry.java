package ripoff.facebook.notification.preference.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ripoff.facebook.notification.commons.NotificationMethod;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExternalNotificationPreferenceEntry implements NotificationPreferenceEntry {

    @Id
    @SequenceGenerator(
            sequenceName = "notification_entry_id_sequence",
            name = "notification_entry_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_entry_id_sequence"
    )
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
