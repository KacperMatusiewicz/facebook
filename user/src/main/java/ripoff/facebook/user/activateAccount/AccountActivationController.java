package ripoff.facebook.user.activateAccount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("api/v1/user/activate")
@RequiredArgsConstructor
public class AccountActivationController {

    private final AccountActivationService service;

    @GetMapping("{key}")
    public void activateUser(@PathVariable Long key) {
        log.info("Received request to activate user.");
        service.activateUserAccount(key);
    }

}
