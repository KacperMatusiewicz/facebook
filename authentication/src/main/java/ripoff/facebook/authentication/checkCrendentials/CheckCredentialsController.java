package ripoff.facebook.authentication.checkCrendentials;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/password")
@RequiredArgsConstructor
public class CheckCredentialsController {

    private final CheckCredentialsService service;

    @GetMapping
    public Boolean checkCredentials(@RequestHeader("user-id") Long userId, @RequestParam() String password) {
        System.out.println(userId);
        System.out.println(password);
        return service.checkCredentials(userId, password);
    }

}
