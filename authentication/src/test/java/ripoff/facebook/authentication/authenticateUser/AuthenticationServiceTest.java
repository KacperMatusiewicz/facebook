package ripoff.facebook.authentication.authenticateUser;

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
import ripoff.facebook.authentication.commons.SessionEntry;
import ripoff.facebook.authentication.commons.SessionRepository;
import ripoff.facebook.clients.authentication.AuthClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.clients.search.SearchClient;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    AuthenticationService underTest;

    @Mock
    SessionRepository sessionRepository;

    SessionEntry se;

    @BeforeEach
    void setUp() {
        underTest = new AuthenticationService(sessionRepository);
        se = new SessionEntry();
        se.setUserId(1L);
        se.setCreationDate(LocalDateTime.now());
        se.setSessionId("asd");
    }

    @Test
    void authenticateWhenSessionIdIsValidAndReturnUserId() {
        //given
        given(sessionRepository.findById(any())).willReturn(Optional.of(se));

        //when
        Long result = underTest.authenticateSession(se.getSessionId());

        //then
        assertEquals(se.getUserId(), result);

    }

    @Test
    void doNotAuthenticateWhenSessionIdIsNotValid() {
        //given
        given(sessionRepository.findById(any())).willReturn(
                Optional.empty()
        );

        Executable e = () -> underTest.authenticateSession(any());
        Assertions.assertThrows(InvalidSessionException.class, e);
    }
}