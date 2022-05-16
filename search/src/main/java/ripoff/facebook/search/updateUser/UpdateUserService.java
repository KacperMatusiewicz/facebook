package ripoff.facebook.search.updateUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.search.User;
import ripoff.facebook.search.UserSearchRepository;

@Service
@RequiredArgsConstructor
public class UpdateUserService {

    private final UserSearchRepository repository;
    public void updateUser(User user) {
        repository.save(user);
    }
}
