package ripoff.facebook.notification.preference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInternalNotificationPreferencesRepository extends JpaRepository<InternalNotificationPreference, Long> {
}
