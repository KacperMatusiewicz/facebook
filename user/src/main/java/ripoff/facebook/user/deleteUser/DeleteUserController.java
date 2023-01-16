package ripoff.facebook.user.deleteUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class DeleteUserController {

    private final DeleteUserService service;

    @DeleteMapping
    public void deleteUser(@RequestHeader("user-id") Long userId) {
        log.info("Received request to delete user with id: " + userId);
        service.deleteUser(userId);
    }

}
