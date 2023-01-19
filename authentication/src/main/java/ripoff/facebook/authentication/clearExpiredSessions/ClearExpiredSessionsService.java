package ripoff.facebook.authentication.clearExpiredSessions;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ripoff.facebook.authentication.commons.SessionRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClearExpiredSessionsService {

    private final SessionRepository repository;

    @Scheduled(fixedDelay = 1000)
    public void clearExpiredSessions() {
        this.repository.clearAllExpiredSessions(LocalDateTime.now());
    }
}
