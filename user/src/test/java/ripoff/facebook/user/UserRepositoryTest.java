package ripoff.facebook.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ripoff.facebook.user.entity.User;
import ripoff.facebook.user.entity.UserStatus;
import ripoff.facebook.user.repository.UserRepository;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void shouldCheckThatEmailExists() {
        //given
        String email = "jankowalski@gmail.com";
        userRepository.save(
                User.builder()
                        .name("Jan")
                        .lastName("Kowalski")
                        .email(email)
                        .password("password")
                        .userStatus(UserStatus.INACTIVE)
                        .build()
        );
        //when
        boolean result = userRepository.checkIfMailExists(email);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldCheckThatEmailDoesNotExists() {
        //given
        String email = "jankowalski@gmail.com";
        String differentEmail = "janekkowalski@gmail.com";
        userRepository.save(
                User.builder()
                        .name("Jan")
                        .lastName("Kowalski")
                        .email(email)
                        .password("password")
                        .userStatus(UserStatus.INACTIVE)
                        .build()
        );
        //when
        boolean result = userRepository.checkIfMailExists(differentEmail);
        //then
        Assertions.assertFalse(result);
    }
}