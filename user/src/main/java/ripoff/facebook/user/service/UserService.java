package ripoff.facebook.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.user.exceptions.BadUserDataException;
import ripoff.facebook.user.exceptions.EmailExistsException;
import ripoff.facebook.user.mail.MailingService;
import ripoff.facebook.user.UserRequest;
import ripoff.facebook.user.entity.User;
import ripoff.facebook.user.entity.UserStatus;
import ripoff.facebook.user.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;
    private MailingService mailingService;
    private UserValidationService userValidationService;

    public void registerUser(UserRequest userRequest) {

        if (!userValidationService.validateUserInputData(userRequest)) {
            throw new BadUserDataException("Error validating user data.");
        }
        if(repository.checkIfMailExists(userRequest.getEmail())) {
            throw new EmailExistsException("Email exists.");
        }

        repository.save(
                User.builder()
                        .name(userRequest.getName())
                        .lastName(userRequest.getLastName())
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .userStatus(UserStatus.INACTIVE)
                        .build()
        );
        mailingService.sendConfirmationEmail(userRequest.getEmail(), userRequest.getName());
    }

    public boolean checkIfUserExistsById(Long userId) {
        return repository.existsById(userId);
    }
}
