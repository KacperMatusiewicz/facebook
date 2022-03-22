package ripoff.facebook.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ripoff.facebook.user.mail.MailingService;
import ripoff.facebook.user.UserRepository;
import ripoff.facebook.user.UserRequest;
import ripoff.facebook.user.entity.User;
import ripoff.facebook.user.entity.UserStatus;
import ripoff.facebook.user.exceptions.BadUserDataException;
import ripoff.facebook.user.exceptions.EmailExistsException;

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
    MailingService mailingService;
    @Mock
    UserValidationService validationService;

    UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(userRepository, mailingService, validationService);
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
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(mailingService).sendConfirmationEmail(emailCaptor.capture(), nameCaptor.capture());
        String capturedEmail = emailCaptor.getValue();
        String capturedName = nameCaptor.getValue();
        assertThat(capturedEmail+capturedName).isEqualTo(userRequest.getEmail()+userRequest.getName());
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
        verify(mailingService, never()).sendConfirmationEmail(anyString(), anyString());
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
        assertThatThrownBy(()-> service.registerUser(userRequest)).isInstanceOf(BadUserDataException.class);
        verify(userRepository, never()).save(any(User.class));
        verify(mailingService, never()).sendConfirmationEmail(anyString(), anyString());
    }

    @Test
    void shouldCallUserExistsById(){
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

    void shoudNotValidateThatUserExistsById(){

    }

}