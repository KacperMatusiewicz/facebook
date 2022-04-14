package ripoff.facebook.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.notification.UserNotificationQueueClient;
import ripoff.facebook.clients.post.PostClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.user.UserRequest;
import ripoff.facebook.user.entity.ActivationLink;
import ripoff.facebook.user.entity.User;
import ripoff.facebook.user.entity.UserStatus;
import ripoff.facebook.user.exceptions.BadUserDataException;
import ripoff.facebook.user.exceptions.EmailExistsException;
import ripoff.facebook.user.mail.ActivationEmail;
import ripoff.facebook.user.mail.EmailAccountActivation;
import ripoff.facebook.user.repository.ActivationRepository;
import ripoff.facebook.user.repository.UserRepository;

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
        validateUserData(userRequest);
        checkIfMailExists(userRequest);
        User user = saveUser(userRequest);
        ActivationLink activationLink = createActivationLink(user);
        sendActivationEmail(user, activationLink);
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

    private void sendActivationEmail(User user, ActivationLink activationLink) {
        ActivationEmail activationEmail = ActivationEmail.builder()
                .key(Long.toString(activationLink.getKey()))
                .recipientAddress(user.getEmail())
                .name(user.getName())
                .build();
        emailAccountActivationService.sendActivationEmail(activationEmail);
    }

    private ActivationLink createActivationLink(User user) {
        return activationRepository.save(
                ActivationLink
                        .builder()
                        .user(user)
                        .build()
        );
    }

    private User saveUser(UserRequest userRequest) {
        return userRepository.save(
                User.builder()
                        .name(userRequest.getName())
                        .lastName(userRequest.getLastName())
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .userStatus(UserStatus.INACTIVE)
                        .build()
        );
    }

    private void checkIfMailExists(UserRequest userRequest) {
        if (userRepository.checkIfMailExists(userRequest.getEmail())) {
            throw new EmailExistsException("Email exists.");
        }
    }

    private void validateUserData(UserRequest userRequest) {
        if (!userValidationService.validateUserInputData(userRequest)) {
            throw new BadUserDataException("Error validating user data.");
        }
    }
}