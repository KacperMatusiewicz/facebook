package ripoff.facebook.notification.preference.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalNotificationPreference {

    @Id
    Long userId;

    @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "internal_notification_preference_id")
    List<InternalNotificationPreferenceEntry> preferences;
}
