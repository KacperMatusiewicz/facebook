package ripoff.facebook.user.getUserDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.user.commons.User;
import ripoff.facebook.user.commons.UserRepository;

@Service
@RequiredArgsConstructor
public class GetUserDetailsService {

    private final UserDetailsViewRepository userDetailsViewRepository;

    public UserDetailsDto getUserDetails(Long userId) {
        UserDetailsView user = userDetailsViewRepository.getById(userId);
        return new UserDetailsDto(
                user.getName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
