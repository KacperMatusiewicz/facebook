package ripoff.facebook.search.addUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.search.User;
import ripoff.facebook.search.UserSearchRepository;

@Service
@RequiredArgsConstructor
public class AddUserService {

    private final UserSearchRepository repository;

    public void addUser(User user) {
        repository.save(user);
    }

}
