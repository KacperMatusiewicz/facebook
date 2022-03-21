package ripoff.facebook.notification.preferences;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Map;

@Entity
@Data
public class NotificationPreference {

    @Id
    Long userId;

    @OneToMany
    List<NotificationEntry> preferences;
}
