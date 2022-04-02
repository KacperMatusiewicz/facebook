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
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if(email == null) {
            return false;
        }
        return checkIfPatternMatches(emailRegex, email);
    }

    public boolean validatePassword(String password){
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[#?!@$%^&*-]).{8,}$";
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
