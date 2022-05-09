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
    private final UserAuthenticationDataRepository repository;

    public Boolean checkCredentials(Long userId, String password) {

        UserAuthenticationData user = getUserAuthenticationData(userId);

        return verifyPassword(password, user);

    }

    private Boolean verifyPassword(String password, UserAuthenticationData user) {
        if(passwordEncoder.matches(password, user.getPassword())){
            return true;
        } else return false;
    }

    private UserAuthenticationData getUserAuthenticationData(Long userId) {
        UserAuthenticationData user = repository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User does not exist.")
        );
        return user;
    }
}
