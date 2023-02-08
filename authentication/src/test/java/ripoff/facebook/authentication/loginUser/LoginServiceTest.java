package ripoff.facebook.authentication.loginUser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ripoff.facebook.authentication.commons.SessionEntry;
import ripoff.facebook.authentication.commons.SessionRepository;
import ripoff.facebook.authentication.commons.UserAuthenticationData;
import ripoff.facebook.authentication.commons.UserAuthenticationDataRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    private LoginService underTest;
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserAuthenticationDataRepository userAuthenticationDataRepository;
    @Mock
    private SessionRepository sessionRepository;

    UserAuthenticationData userAuthenticationData;
    private final String password = "password";
    private final Long userId = 1L;

    private final String login = "login";
    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        underTest = new LoginService(passwordEncoder,userAuthenticationDataRepository, sessionRepository);
        userAuthenticationData = new UserAuthenticationData(userId, login, passwordEncoder.encode(password));
    }

    @Test
    void throwWhenUserNotFound(){
        //given
        given(userAuthenticationDataRepository.getByLogin(any())).willReturn(Optional.empty());

        //when
        Executable e = () -> underTest.loginUser(new UserCredentials("asdf", "password"));

        //then
        Assertions.assertThrows(LoginNotFoundException.class, e);
    }

    @Test
    void throwExceptionWhenPasswordIsIncorrect() {
        //given
        UserCredentials credentials = new UserCredentials();
        credentials.setLogin(login);
        credentials.setPassword("Wrong_password1234");

        given(userAuthenticationDataRepository.getByLogin(login)).willReturn(Optional.of(userAuthenticationData));

        //when
        Executable e = () -> underTest.loginUser(credentials);

        //then
        Assertions.assertThrows(InvalidCredentialsException.class, e);
    }

    @Test
    void createNewSessionWhenPasswordMatches() {
        //given
        UserCredentials credentials = new UserCredentials();
        credentials.setLogin(login);
        credentials.setPassword(password);
        given(userAuthenticationDataRepository.getByLogin(login)).willReturn(Optional.of(userAuthenticationData));
        //when
        String returnedSessionId = underTest.loginUser(credentials);
        //then
        ArgumentCaptor<SessionEntry> sessionEntryArgumentCaptor = ArgumentCaptor.forClass(SessionEntry.class);
        verify(sessionRepository, times(1)).save(sessionEntryArgumentCaptor.capture());
        Assertions.assertEquals(returnedSessionId, sessionEntryArgumentCaptor.getValue().getSessionId());
    }
}