package ripoff.facebook.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ripoff.facebook.mail.MailingService;
import ripoff.facebook.user.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    MailingService mailingService;
    @Mock
    UserValidationService validationService;

    UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(userRepository, mailingService, validationService);
    }

    @Test
    void shouldCallUserRepositoryToCheckIfEmailExists() {
    }

    @Test
    void shouldCallUserRepositoryToSaveUser() {
    }

    @Test
    void shouldCallMailingServiceToSendConfirmationEmail() {

    }

    @Test
    void shouldThrowEmailExistsExceptionIfEmailExists() {
    }

    @Test
    void shouldThrowBadUserDataExceptionWhenDataArentValidated() {
    }

}