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

    /**
     * GET api/v1/auth/password <br>
     * Checks user password validity.
     * @param userId header "user-id" - represents user id
     * @param password request parameter "password"
     * @return true: credentials are valid, false: invalid credentials
     */
    @GetMapping
    public Boolean checkCredentials(@RequestHeader("user-id") Long userId, @RequestParam() String password) {
        log.info("Received validate credentials request.");
        return service.checkCredentials(userId, password);
    }

}
