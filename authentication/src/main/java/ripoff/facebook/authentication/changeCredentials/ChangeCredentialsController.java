package ripoff.facebook.authentication.changeCredentials;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/v1/auth/password")
@RequiredArgsConstructor
public class ChangeCredentialsController {

    private final ChangeCredentialsService service;

    /**
     * Changes user password
     * @param userId user id
     * @param changePasswordDto json body containing old password: string, new password: string
     */
    @PutMapping()
    public void changePassword(@RequestHeader("user-id") Long  userId, @RequestBody ChangePasswordDto changePasswordDto){
        log.info("Received change password request.");
        service.changePassword(userId, changePasswordDto);
    }
}
