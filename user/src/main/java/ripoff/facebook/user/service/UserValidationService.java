package ripoff.facebook.user.service;

import org.springframework.stereotype.Service;
import ripoff.facebook.user.UserRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserValidationService {

    public boolean validateUserInputData(UserRequest userRequest) {
        return validateName(userRequest.getName())
                && validateLastName(userRequest.getLastName())
                && validateEmail(userRequest.getEmail())
                && validatePassword(userRequest.getPassword());
    }

    public boolean validateName(String name){
        String nameRegex = "[a-zA-Z]+";
        if(name == null) {
            return false;
        }
        return checkIfPatternMatches(nameRegex, name);

    }

    public boolean validateLastName(String lastName){
        String lastNameRegex = "[a-zA-Z]+[\\-]?[a-zA-Z]+";
        if(lastName == null) {
            return false;
        }
        return checkIfPatternMatches(lastNameRegex, lastName);
    }

    public boolean validateEmail(String email){
        String emailRegex = "[^\\;@\\.]+[@][^\\;@]+[\\.][^\\;@]+";
        if(email == null) {
            return false;
        }
        return checkIfPatternMatches(emailRegex, email);
    }

    public boolean validatePassword(String password){
        String passwordRegex = "[^\\;]{6,}";
        if(password == null) {
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
