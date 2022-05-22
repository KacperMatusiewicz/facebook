package ripoff.facebook.user.commons;

import org.springframework.stereotype.Service;
import ripoff.facebook.user.createUser.UserRequest;
import ripoff.facebook.user.updateUser.ContactInfoDto;
import ripoff.facebook.user.updateUser.PersonalInfoDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserValidationService {

    public boolean validateUserRegistrationData(UserRequest userRequest) {
        return validateName(userRequest.getName())
                && validateLastName(userRequest.getLastName())
                && validateEmail(userRequest.getEmail())
                && validatePassword(userRequest.getPassword());
    }

    public boolean validateUserPersonalInfo(PersonalInfoDto personalInfoDto) {
        return validateName(personalInfoDto.getName())
                && validateLastName(personalInfoDto.getLastName());
    }

    public boolean validateUserContactInfo(ContactInfoDto contactInfoDto) {
        return validateEmail(contactInfoDto.getEmail());
    }

    private boolean validateName(String name) {
        String nameRegex = "([a-zA-Z\\u00C0-\\u1FFF\\u2C00-\\uD7FF\\w',.-]+( [a-zA-Z\\u00C0-\\u1FFF\\u2C00-\\uD7FF\\w',.-]+)*){2,30}";
        if (name == null) {
            return false;
        }
        return checkIfPatternMatches(nameRegex, name);
    }

    private boolean validateLastName(String lastName) {
        String lastNameRegex = "([a-zA-Z\\u00C0-\\u1FFF\\u2C00-\\uD7FF\\w',.-]+( [a-zA-Z\\u00C0-\\u1FFF\\u2C00-\\uD7FF\\w',.-]+)*){2,30}";
        if (lastName == null) {
            return false;
        }
        return checkIfPatternMatches(lastNameRegex, lastName);
    }

    private boolean validateEmail(String email) {
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if (email == null) {
            return false;
        }
        return checkIfPatternMatches(emailRegex, email);
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
