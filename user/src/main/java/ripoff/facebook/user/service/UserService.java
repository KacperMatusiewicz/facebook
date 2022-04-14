package ripoff.facebook.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.notification.UserNotificationQueueClient;
import ripoff.facebook.clients.post.PostClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.user.entity.ActivationLink;
import ripoff.facebook.user.exceptions.BadUserDataException;
import ripoff.facebook.user.exceptions.EmailExistsException;
import ripoff.facebook.user.mail.ActivationEmail;
import ripoff.facebook.user.mail.EmailAccountActivation;
import ripoff.facebook.user.mail.EmailAccountActivationService;
import ripoff.facebook.user.UserRequest;
import ripoff.facebook.user.entity.User;
import ripoff.facebook.user.entity.UserStatus;
import ripoff.facebook.user.repository.UserRepository;
import ripoff.facebook.user.repository.ActivationRepository;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private ActivationRepository activationRepository;
    private EmailAccountActivation emailAccountActivationService;
    private UserValidationService userValidationService;
    private RelationClient relationClient;
    private UserNotificationQueueClient userNotificationQueueClient;
    private PostClient postClient;

    public void registerUser(UserRequest userRequest) {

        if (!userValidationService.validateUserInputData(userRequest)) {
            throw new BadUserDataException("Error validating user data.");
        }
        if(userRepository.checkIfMailExists(userRequest.getEmail())) {
            throw new EmailExistsException("Email exists.");
        }

        User user = userRepository.save(
                User.builder()
                        .name(userRequest.getName())
                        .lastName(userRequest.getLastName())
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .userStatus(UserStatus.INACTIVE)
                        .build()
        );
        ActivationLink activationLink = activationRepository.save(ActivationLink.builder().user(user).build());
        ActivationEmail activationEmail = ActivationEmail.builder()
                .key(Long.toString(activationLink.getKey()))
                .recipientAddress(user.getEmail())
                .name(user.getName())
                .build();
        emailAccountActivationService.sendActivationEmail(activationEmail);
    }

    public void deleteUser(Long userId) {
        userNotificationQueueClient.deleteQueue(userId);
        relationClient.deleteUser(userId);
        postClient.deleteAllPostsByUserId(userId);
        userRepository.deleteById(userId);
    }

    public boolean checkIfUserExistsById(Long userId) {
        return userRepository.existsById(userId);
    }
}