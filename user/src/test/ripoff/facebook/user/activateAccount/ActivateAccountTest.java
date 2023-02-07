package ripoff.facebook.user.activateAccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ripoff.facebook.clients.authentication.AuthClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.clients.search.SearchClient;
import ripoff.facebook.user.commons.*;
import ripoff.facebook.user.createUser.UserRequest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ActivateAccountTest {

    private AccountActivationService underTest;
    @Mock
    private ActivationRepository activationRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RelationClient relationClient;
    @Mock
    private AuthClient authClient;
    @Mock
    private SearchClient searchClient;

    User user;
    ActivationLink activationLink;

    @BeforeEach
    void setUp() {
        underTest = new AccountActivationService(
                activationRepository,
                userRepository,
                relationClient,
                authClient,
                searchClient
        );

        user =  new User(
                1L,
                "a",
                "b",
                "c",
                "d",
                UserStatus.INACTIVE,
                LocalDateTime.now().minus(1, ChronoUnit.HOURS),
                null
        );
        activationLink = new ActivationLink(
                1L, user, LocalDateTime.now().minus(1, ChronoUnit.HOURS), false
        );
        user.setActivationLink(activationLink);

    }

    private UserRequest createRequest(){
        UserRequest request = new UserRequest();
        request.setName("John");
        request.setLastName("Smith");
        request.setEmail("example@mail.com");
        request.setPassword("Example12345");
        return request;
    }

    @Test
    public void shouldChangeStatusToActive(){
        //given
        UserRequest request = createRequest();
        given(activationRepository.existsById(any())).willReturn(true);
        given(activationRepository.existsById(any())).willReturn(true);
        given(activationRepository.getById(any())).willReturn(activationLink);
        //when
        underTest.activateUserAccount(any());

        //then
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(any());
        verify(userRepository).save(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getUserStatus(), UserStatus.ACTIVE);
    }

    @Test
    public void shouldInitializeData(){
        //given
        UserRequest request = createRequest();
        given(activationRepository.existsById(any())).willReturn(true);
        given(activationRepository.getById(any())).willReturn(activationLink);
        //when
        underTest.activateUserAccount(any());

        //then
        verify(authClient, times(1)).registerNewUserAuthenticationMethod(any());
        verify(relationClient, times(1)).createUser(any());
        verify(searchClient, times(1)).addUser(any());
    }

    @Test
    public void shouldThrowExceptionWhenActivationLinkDoesNotExist(){
        //given
        given(activationRepository.existsById(any())).willReturn(false);
        //when
        Executable e = () -> underTest.activateUserAccount(any());
        //then
        Assertions.assertThrows(ActivationLinkNotFound.class, e);

    }

}