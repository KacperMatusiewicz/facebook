package ripoff.facebook.search.searchUsers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.search.User;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class SearchUsersController {
    private final SearchUsersService service;

    @GetMapping
    public List<User> searchUser(@RequestParam String searchedPhrase) {
        log.info("Received request to search user.");
        return service.searchUser(
                new QueryParameters(searchedPhrase)
        );
    }
}
