package ripoff.facebook.authentication.checkCrendentials;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ripoff.facebook.authentication.commons.UserNotFoundException;
import ripoff.facebook.authentication.commons.UserAuthenticationData;
import ripoff.facebook.authentication.commons.UserAuthenticationDataRepository;

@Service
@RequiredArgsConstructor
public class CheckCredentialsService {

    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationDataViewRepository repository;

    public Boolean checkCredentials(Long userId, String password) {

        UserAuthenticationDataView user = getUserAuthenticationData(userId);

        return verifyPassword(password, user);

    }

    private Boolean verifyPassword(String password, UserAuthenticationDataView user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    private UserAuthenticationDataView getUserAuthenticationData(Long userId) {
        UserAuthenticationDataView user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist.")
        );
        return user;
    }
}
