package ripoff.facebook.authentication.commons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntry, String> {

    void deleteAllByUserId(Long id);

    @Procedure("clear_all_expired_sessions")
    void clearAllExpiredSessions(LocalDateTime now);
}
