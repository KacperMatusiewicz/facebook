package ripoff.facebook.search.removeUser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class RemoveUserController {
    private final RemoveUserService service;

    @DeleteMapping("{id}")
    public void removeUser(@PathVariable Long id) {
        service.removeUserById(id);
    }
}
