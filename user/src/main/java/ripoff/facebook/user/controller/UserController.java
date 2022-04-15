package ripoff.facebook.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.clients.user.UserExistsResponse;
import ripoff.facebook.user.UserRequest;
import ripoff.facebook.user.service.UserService;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserRequest userRequest) {
        System.out.println("dziala");
        userService.registerUser(userRequest);
    }

    @GetMapping("{userId}")
    public UserExistsResponse checkIfUserExistsById(@PathVariable Long userId) {
        return new UserExistsResponse(userService.checkIfUserExistsById(userId));
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
