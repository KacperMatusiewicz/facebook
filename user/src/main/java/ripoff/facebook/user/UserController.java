package ripoff.facebook.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.user.service.UserService;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.registerUser(userRequest);
    }

}
