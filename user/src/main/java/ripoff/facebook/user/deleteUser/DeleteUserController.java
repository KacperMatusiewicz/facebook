package ripoff.facebook.user.deleteUser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class DeleteUserController {

    private final DeleteUserService service;

    @DeleteMapping
    public void deleteUser(@RequestHeader("user-id") Long userId) {
        service.deleteUser(userId);
    }

}
