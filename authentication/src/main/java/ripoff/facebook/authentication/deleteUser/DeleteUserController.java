package ripoff.facebook.authentication.deleteUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("api/v1/auth/delete")
@RequiredArgsConstructor
public class DeleteUserController {

    private final DeleteUserService service;

    @DeleteMapping("{userId}")
    public void deleteUserAuthenticationData(@PathVariable Long userId){
        log.info("Received delete user request.");
        service.deleteUserAuthenticationData(userId);
    }
}
