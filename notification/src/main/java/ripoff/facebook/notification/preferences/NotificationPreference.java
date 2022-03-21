package ripoff.facebook.notification.preferences;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationPreference {

    @Id
    Long userId;

    @OneToMany(cascade = CascadeType.ALL)
    List<NotificationEntry> preferences;
}
