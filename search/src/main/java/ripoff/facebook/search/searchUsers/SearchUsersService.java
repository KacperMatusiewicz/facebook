package ripoff.facebook.search.searchUsers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.search.User;
import ripoff.facebook.search.UserSearchRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchUsersService {
    private final UserSearchRepository.UserSearchInternalImplementation repo;

    public List<User> searchUser(QueryParameters queryParameters){
        return repo.search(queryParameters.getSearchedPhrase());
    }
}
