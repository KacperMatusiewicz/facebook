package ripoff.facebook.user.createUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class CreateUserController {

    private final CreateUserService service;

    @PostMapping
    public String createUser(@RequestBody UserRequest userRequest) {
        log.info("Received request to create user.");
        return service.registerUser(userRequest);
    }

}
