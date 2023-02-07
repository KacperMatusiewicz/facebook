package ripoff.facebook.user.createUser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ripoff.facebook.user.commons.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateUserTest {

    private CreateUserService underTest;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserValidationService userValidationService;
    @Mock
    private ActivationRepository activationRepository;
    @Mock
    private EmailAccountActivationService emailAccountActivationService;

    ActivationLink activationLink;
    User user;

    @BeforeEach
    void setUp() {
        underTest = new CreateUserService(
                userRepository,
                userValidationService,
                activationRepository,
                emailAccountActivationService
        );
        activationLink = new ActivationLink(
                1L, null, LocalDateTime.now().minus(1, ChronoUnit.HOURS), false
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
    public void shouldCreateInactiveUser(){
        //given
        UserRequest request = createRequest();
        given(userRepository.checkIfMailExists(anyString())).willReturn(false);
        given(userValidationService.validateUserRegistrationData(any())).willReturn(true);
        given(activationRepository.save(any())).willReturn(activationLink);
        given(userRepository.save(any())).willReturn(user);

        //when
        underTest.registerUser(request);

        //then
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void shouldThrowExceptionWhenEmailAlreadyInUse(){
        //given
        UserRequest request = createRequest();
        given(userRepository.checkIfMailExists(anyString())).willReturn(true);
        given(userValidationService.validateUserRegistrationData(any())).willReturn(true);
        //when
        Executable e = () -> underTest.registerUser(request);
        //then
        Assertions.assertThrows(EmailExistsException.class, e);

    }

    @Test
    public void shouldThrowExceptionWhenUserDataIsInvalid(){
        //given
        UserRequest request = createRequest();
        given(userValidationService.validateUserRegistrationData(any())).willReturn(false);
        //when
        Executable e = () -> underTest.registerUser(request);
        //then
        Assertions.assertThrows(BadUserDataException.class, e);

    }
}