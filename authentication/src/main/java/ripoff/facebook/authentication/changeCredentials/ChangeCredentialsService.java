package ripoff.facebook.authentication.changeCredentials;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ripoff.facebook.authentication.commons.UserAuthenticationData;
import ripoff.facebook.authentication.commons.UserAuthenticationDataRepository;
import ripoff.facebook.authentication.commons.UserNotFoundException;
import ripoff.facebook.authentication.loginUser.InvalidCredentialsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ChangeCredentialsService {

    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationDataRepository repository;
    public void changePassword(Long userId, ChangePasswordDto changePasswordDto) {
        validateNewPassword(changePasswordDto);
        UserAuthenticationData user = getUserAuthenticationData(userId);
        validateOldPassword(changePasswordDto, user);
        saveNewPassword(userId, changePasswordDto, user);
    }

    private void validateNewPassword(ChangePasswordDto changePasswordDto) {
        if(!validatePassword(changePasswordDto.getNewPassword())){
            throw new PasswordFormatException("New password doesn't match password policy.");
        }
    }

    private UserAuthenticationData getUserAuthenticationData(Long userId) {
        return repository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found.")
        );
    }

    private void validateOldPassword(ChangePasswordDto changePasswordDto, UserAuthenticationData user) {
        if(!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Current password doesn't match.");
        }
    }

    private void saveNewPassword(Long userId, ChangePasswordDto changePasswordDto, UserAuthenticationData user) {
        UserAuthenticationData newAuthenticationData = new UserAuthenticationData(
                userId,
                user.getLogin(),
                passwordEncoder.encode(
                        changePasswordDto.getNewPassword()
                )
        );
        repository.save(newAuthenticationData);
    }

    private boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z]).{8,}$";
        //special character: (?=.*?[#?!@$%^&*-])
        if (password == null) {
            return false;
        }
        return checkIfPatternMatches(passwordRegex, password);
    }

    private boolean checkIfPatternMatches(String pattern, String input) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(input);
        return matcher.matches();
    }
}
