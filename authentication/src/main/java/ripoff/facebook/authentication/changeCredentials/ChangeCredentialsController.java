package ripoff.facebook.authentication.changeCredentials;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/password")
@RequiredArgsConstructor
public class ChangeCredentialsController {

    private final ChangeCredentialsService service;

    @PutMapping()
    public void changePassword(@RequestHeader("user-id") Long  userId, @RequestBody ChangePasswordDto changePasswordDto){
        System.out.println(userId);
        System.out.println(changePasswordDto.getOldPassword());
        System.out.println(changePasswordDto.getNewPassword());
        service.changePassword(userId, changePasswordDto);
    }
}
