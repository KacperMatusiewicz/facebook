package ripoff.facebook.user.checkIfUserExist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.user.commons.UserRepository;

@Service
@RequiredArgsConstructor
public class CheckIfUserExistsService {

    private final UserRepository repository;
    //TODO: View
    public boolean checkIfUserExistsById(Long userId) {
        return repository.existsById(userId);
    }

}
