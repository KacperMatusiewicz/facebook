package ripoff.facebook.search.removeUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.search.UserSearchRepository;

@Service
@RequiredArgsConstructor
public class RemoveUserService {

    private final UserSearchRepository repository;

    public void removeUserById(Long id){
        repository.deleteById(id);
    }
}
