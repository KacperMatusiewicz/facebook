package ripoff.facebook.authentication.loginUser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ripoff.facebook.authentication.commons.SessionEntry;
import ripoff.facebook.authentication.commons.SessionRepository;
import ripoff.facebook.authentication.commons.UserAuthenticationData;
import ripoff.facebook.authentication.commons.UserAuthenticationDataRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationDataRepository userRepository;
    private final SessionRepository sessionRepository;

    public String loginUser(UserCredentials userCredentials) {

        UserAuthenticationData userAuthenticationData = userRepository
                .getByLogin(userCredentials.getLogin())
                .orElseThrow(
                    () -> new LoginNotFoundException("Login not Found")
                );

        if (passwordEncoder.matches(userCredentials.getPassword(), userAuthenticationData.getPassword())) {
            String sessionId = UUID.randomUUID().toString();
            sessionRepository.save(new SessionEntry(sessionId, userAuthenticationData.getId(), LocalDateTime.now()));
            return sessionId;
        } else {
            throw new InvalidCredentialsException("Password not correct.");
        }
    }

    public void logoutUser(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    public void logoutUserFromAllSessions(Long userId) {
        sessionRepository.deleteAllByUserId(userId);
    }
}
