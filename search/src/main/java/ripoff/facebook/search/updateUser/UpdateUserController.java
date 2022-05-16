package ripoff.facebook.search.updateUser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.clients.search.UserRequest;
import ripoff.facebook.search.User;

@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class UpdateUserController {
    private final UpdateUserService service;

    @PutMapping
    public void updateUser(@RequestBody UserRequest userRequest) {
        service.updateUser(new User(userRequest.getId(), userRequest.getName(), userRequest.getLastName()));
    }
}
