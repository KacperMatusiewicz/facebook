package ripoff.facebook.authentication.deleteUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.authentication.commons.SessionRepository;
import ripoff.facebook.authentication.commons.UserAuthenticationDataRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserService {

    private final UserAuthenticationDataRepository repository;

    @Transactional
    public void deleteUserAuthenticationData(Long userId) {
        repository.deleteById(userId);
    }
}
