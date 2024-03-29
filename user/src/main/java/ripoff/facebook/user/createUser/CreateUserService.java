package ripoff.facebook.user.createUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.user.commons.*;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;
    private final UserValidationService userValidationService;
    private final ActivationRepository activationRepository;
    private  final EmailAccountActivationService emailAccountActivationService;

    public String registerUser(UserRequest userRequest) {
        validateUserData(userRequest);
        checkIfMailExists(userRequest);
        User user = saveUser(userRequest);
        ActivationLink activationLink = createActivationLink(user);
        sendActivationEmail(user, activationLink);
        return user.getEmail();
    }

    private void validateUserData(UserRequest userRequest) {
        if (!userValidationService.validateUserRegistrationData(userRequest)) {
            throw new BadUserDataException("Error validating user data." + userRequest);
        }
    }
    private void checkIfMailExists(UserRequest userRequest) {
        //TODO: View
        if (userRepository.checkIfMailExists(userRequest.getEmail())) {
            throw new EmailExistsException("Email exists.");
        }
    }
    private User saveUser(UserRequest userRequest) {
        return userRepository.save(
                User.builder()
                        .name(userRequest.getName())
                        .lastName(userRequest.getLastName())
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .userStatus(UserStatus.INACTIVE)
                        .creationDate(LocalDateTime.now())
                        .build()
        );
    }

    private ActivationLink createActivationLink(User user) {
        return activationRepository.save(
                ActivationLink
                        .builder()
                        .user(user)
                        .creationDate(LocalDateTime.now())
                        .used(false)
                        .build()
        );
    }

    private void sendActivationEmail(User user, ActivationLink activationLink) {
        ActivationEmail activationEmail = ActivationEmail.builder()
                .key(Long.toString(activationLink.getKey()))
                .recipientAddress(user.getEmail())
                .name(user.getName())
                .build();
        emailAccountActivationService.sendActivationEmail(activationEmail);
    }
}
