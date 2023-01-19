package ripoff.facebook.authentication.authenticateUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.authentication.commons.SessionEntry;
import ripoff.facebook.authentication.commons.SessionRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final SessionRepository sessionRepository;
    public Long authenticateSession(String sessionId) {
        Long session = sessionRepository
                .findById(sessionId)
                .orElseThrow(() ->new InvalidSessionException("Session id not found"))
                .getUserId();

        SessionEntry entry = sessionRepository.findById(sessionId).orElseThrow();
        entry.setCreationDate(LocalDateTime.now());
        sessionRepository.save(entry);

        return session;
    }
}
