package ripoff.facebook.user.createUser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class CreateUserController {

    private final CreateUserService service;

    @PostMapping
    public void createUser(@RequestBody UserRequest userRequest) {
        service.registerUser(userRequest);
    }

}
