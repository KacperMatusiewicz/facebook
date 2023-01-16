package ripoff.facebook.search.removeUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class RemoveUserController {
    private final RemoveUserService service;

    @DeleteMapping("{id}")
    public void removeUser(@PathVariable Long id) {
        log.info("Received request to remove user with id: " + id);
        service.removeUserById(id);
    }
}
