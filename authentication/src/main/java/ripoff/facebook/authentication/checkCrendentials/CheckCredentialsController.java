package ripoff.facebook.authentication.checkCrendentials;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/v1/auth/password")
@RequiredArgsConstructor
public class CheckCredentialsController {

    private final CheckCredentialsService service;

    @GetMapping
    public Boolean checkCredentials(@RequestHeader("user-id") Long userId, @RequestParam() String password) {
        log.info("Received validate credentials request.");
        return service.checkCredentials(userId, password);
    }

}
