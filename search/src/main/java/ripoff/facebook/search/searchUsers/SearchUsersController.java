package ripoff.facebook.search.searchUsers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.search.User;

import java.util.List;

@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class SearchUsersController {
    private final SearchUsersService service;

    @GetMapping
    public List<User> searchUser(@RequestParam String searchedPhrase) {
        return service.searchUser(
                new QueryParameters(searchedPhrase)
        );
    }
}
