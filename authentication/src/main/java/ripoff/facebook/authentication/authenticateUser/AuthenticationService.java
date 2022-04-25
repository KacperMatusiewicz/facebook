package ripoff.facebook.authentication.authenticateUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.authentication.commons.SessionRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final SessionRepository sessionRepository;
    public Long authenticateSession(String sessionId) {
        return sessionRepository
                .findById(sessionId)
                .orElseThrow(() ->new InvalidSessionException("Session id not found"))
                .getUserId();
    }
}
