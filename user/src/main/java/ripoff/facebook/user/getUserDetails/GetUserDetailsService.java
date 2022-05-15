package ripoff.facebook.user.getUserDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.user.commons.User;
import ripoff.facebook.user.commons.UserRepository;

@Service
@RequiredArgsConstructor
public class GetUserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsDto getUserDetails(Long userId) {
        User user = userRepository.getById(userId);
        return new UserDetailsDto(
                user.getName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
