package ripoff.facebook.notification.preferences;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ripoff.facebook.notification.preferences.NotificationPreference;

@Repository
public interface UserNotificationPreferencesRepository extends JpaRepository<NotificationPreference, Long> {
}
