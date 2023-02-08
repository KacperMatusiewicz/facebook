package ripoff.facebook.authentication.checkCrendentials;

import static org.junit.jupiter.api.Assertions.*;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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
import ripoff.facebook.authentication.commons.UserNotFoundException;
import ripoff.facebook.clients.authentication.AuthClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.clients.search.SearchClient;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class CheckCredentialsServiceTest {
    CheckCredentialsService underTest;
    PasswordEncoder passwordEncoder;

    UserAuthenticationDataView entity;
    @Mock
    UserAuthenticationDataViewRepository repository;

    private final String password =  "password";
    private final Long userId = 1L;
    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        entity = new UserAuthenticationDataView(userId, "user", passwordEncoder.encode(password));
        underTest = new CheckCredentialsService(passwordEncoder, repository);
    }

    @Test
    void verifyPasswordWhenPasswordIsCorrect(){
        //given
        given(repository.findById(userId)).willReturn(Optional.of(entity));
        //when
        boolean result = underTest.checkCredentials(userId, password);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    void doNotVerifyPasswordWhenPasswordIsCorrect(){
        //given
        String providedPassword = "incorrect_password";
        given(repository.findById(userId)).willReturn(Optional.of(entity));
        //when
        boolean result = underTest.checkCredentials(userId, providedPassword);
        Assertions.assertFalse(result);
    }

    @Test
    void throwExceptionWhenUserNotFound() {
        //given
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //when
        Executable e = () -> underTest.checkCredentials(userId, password);
        //then
        Assertions.assertThrows(UserNotFoundException.class, e);
    }
}