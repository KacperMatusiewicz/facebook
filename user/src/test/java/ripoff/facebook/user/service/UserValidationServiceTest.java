package ripoff.facebook.user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ripoff.facebook.user.UserRequest;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationServiceTest {

    UserValidationService userValidationService;

    @BeforeEach
    void setUp() {
        userValidationService = new UserValidationService();
    }

    @Test
    void shouldValidateUserInputData() {
        //given
        UserRequest userRequest = UserRequest.builder()
                .name("Jan")
                .lastName("Kowalski")
                .email("kowalski@gmail.com")
                .password("password")
                .build();
        //when
        boolean result = userValidationService.validateUserInputData(userRequest);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldValidateName() {
        //given
        String name = "Jan";
        //when
        boolean result = userValidationService.validateName(name);
        //then
        Assertions.assertTrue(result);
    }
    @Test
    void shouldNotValidateName() {
        //given
        String name = "Jan;";
        //when
        boolean result = userValidationService.validateName(name);
        //then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldValidateLastName() {
        //given
        String lastName = "Kowalski";
        //when
        boolean result = userValidationService.validateLastName(lastName);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotValidateLastName() {
        //given
        String lastName = "Kow@lski";
        //when
        boolean result = userValidationService.validateLastName(lastName);
        //then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldValidateEmail() {
        //given
        String email = "kowalski@gmail.com";
        //when
        boolean result = userValidationService.validateEmail(email);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotValidateEmail() {
        //given
        String email = "kow@alski@gmail.com";
        //when
        boolean result = userValidationService.validateEmail(email);
        //then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldValidatePassword() {
        String password = "password";
        boolean result = userValidationService.validatePassword(password);
        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotValidatePassword() {
        String password = "1234";
        boolean result = userValidationService.validatePassword(password);
        Assertions.assertFalse(result);
    }
}