package ripoff.facebook.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ripoff.facebook.clients.authentication.AuthClient;
import ripoff.facebook.clients.notification.UserNotificationQueueClient;
import ripoff.facebook.clients.post.PostClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.user.UserRequest;
import ripoff.facebook.user.entity.User;
import ripoff.facebook.user.entity.UserStatus;
import ripoff.facebook.user.exceptions.BadUserDataException;
import ripoff.facebook.user.exceptions.EmailExistsException;
import ripoff.facebook.user.mail.ActivationEmail;
import ripoff.facebook.user.mail.EmailAccountActivationService;
import ripoff.facebook.user.repository.ActivationRepository;
import ripoff.facebook.user.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    ActivationRepository activationRepository;
    @Mock
    EmailAccountActivationService emailAccountActivationService;
    @Mock
    UserValidationService validationService;
    @Mock
    RelationClient relationClient;
    @Mock
    UserNotificationQueueClient userNotificationQueueClient;
    @Mock
    PostClient postClient;
    @Mock
    AuthClient authClient;


    UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(
                userRepository,
                activationRepository,
                emailAccountActivationService,
                validationService,
                relationClient,
                authClient,
                userNotificationQueueClient,
                postClient
        );
    }

    @Test
    void shouldCallUserRepositoryToCheckIfEmailExistsWhenUserRegisters() {
        //given
        UserRequest userRequest = UserRequest.builder()
                .name("Jan")
                .lastName("Kowalski")
                .email("kowalski@gmail.com")
                .password("password")
                .build();
        given(validationService.validateUserInputData(userRequest)).willReturn(true);
        //when
        service.registerUser(userRequest);
        //then
        Mockito.verify(userRepository).checkIfMailExists(userRequest.getEmail());
    }

    @Test
    void shouldCallUserRepositoryToSaveUserWhenUserRegisters() {
        //given
        User expectedUser = User.builder()
                .name("Jan")
                .lastName("Kowalski")
                .email("kowalski@gmail.com")
                .password("password")
                .userStatus(UserStatus.INACTIVE)
                .build();

        UserRequest userRequest = UserRequest.builder()
                .name("Jan")
                .lastName("Kowalski")
                .email("kowalski@gmail.com")
                .password("password")
                .build();
        given(validationService.validateUserInputData(userRequest)).willReturn(true);
        given(userRepository.checkIfMailExists(userRequest.getEmail())).willReturn(false);
        //when
        service.registerUser(userRequest);
        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).usingRecursiveComparison().isEqualTo(expectedUser);
    }

    @Test
    void shouldCallMailingServiceToSendConfirmationEmailWhenUserRegisters() {
        //given
        UserRequest userRequest = UserRequest.builder()
                .name("Jan")
                .lastName("Kowalski")
                .email("kowalski@gmail.com")
                .password("password")
                .build();
        given(validationService.validateUserInputData(userRequest)).willReturn(true);
        given(userRepository.checkIfMailExists(userRequest.getEmail())).willReturn(false);
        //when
        service.registerUser(userRequest);
        //then
        ArgumentCaptor<ActivationEmail> emailCaptor = ArgumentCaptor.forClass(ActivationEmail.class);
        Mockito.verify(emailAccountActivationService).sendActivationEmail(emailCaptor.capture());
        ActivationEmail capturedEmail = emailCaptor.getValue();
        //TODO:
        // assertThat(capturedEmail).isEqualTo(ne);
    }

    @Test
    void shouldCallValidationServiceWhenUserRegisters() {
        //given
        UserRequest userRequest = UserRequest.builder()
                .name("Jan")
                .lastName("Kowalski")
                .email("kowalski@gmail.com")
                .password("password")
                .build();
        given(validationService.validateUserInputData(userRequest)).willReturn(true);
        given(userRepository.checkIfMailExists(userRequest.getEmail())).willReturn(false);
        //when
        service.registerUser(userRequest);
        //then
        ArgumentCaptor<UserRequest> userArgumentCaptor = ArgumentCaptor.forClass(UserRequest.class);
        Mockito.verify(validationService).validateUserInputData(userArgumentCaptor.capture());
        UserRequest capturedUserRequest = userArgumentCaptor.getValue();
        assertThat(capturedUserRequest).usingRecursiveComparison().isEqualTo(userRequest);
    }

    @Test
    void shouldThrowEmailExistsExceptionIfEmailExistsWhenUserRegistersAndShouldNotCallMailingServiceAndUserRepositoryToSave() {
        //given
        UserRequest userRequest = UserRequest.builder()
                .name("Jan")
                .lastName("Kowalski")
                .email("kowalski@gmail.com")
                .password("password")
                .build();

        given(userRepository.checkIfMailExists(anyString())).willReturn(true);
        given(validationService.validateUserInputData(userRequest)).willReturn(true);
        //when
        //then
        assertThatThrownBy(() -> service.registerUser(userRequest)).isInstanceOf(EmailExistsException.class);
        verify(userRepository, never()).save(any(User.class));
        verify(emailAccountActivationService, never()).sendActivationEmail(any());
    }

    @Test
    void shouldThrowBadUserDataExceptionWhenDataArentValidatedWhenUserRegistersAndShouldNotCallMailingServiceAndUserRepositoryToSave() {
        //given
        UserRequest userRequest = UserRequest.builder()
                .name("Jan")
                .lastName("Kowalski")
                .email("kowalski@gmail.com")
                .password("password")
                .build();

        given(validationService.validateUserInputData(any(UserRequest.class))).willReturn(false);
        //when
        //then
        assertThatThrownBy(() -> service.registerUser(userRequest)).isInstanceOf(BadUserDataException.class);
        verify(userRepository, never()).save(any(User.class));
        verify(emailAccountActivationService, never()).sendActivationEmail(any());
    }

    @Test
    void shouldCallUserExistsById() {
        //given
        Long givenUserId = 123L;
        //when
        service.checkIfUserExistsById(givenUserId);
        //then
        ArgumentCaptor<Long> userIdCaptor = ArgumentCaptor.forClass(Long.class);
        verify(userRepository).existsById(userIdCaptor.capture());
        Long capturedUserId = userIdCaptor.getValue();
        assertThat(capturedUserId).isEqualTo(givenUserId);

    }

    void shoudNotValidateThatUserExistsById() {

    }

}