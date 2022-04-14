package ripoff.facebook.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.user.service.UserActivationService;

@RestController
@RequestMapping("api/v1/user/activate")
@AllArgsConstructor
public class ActivationController {

    UserActivationService userActivationService;

    @GetMapping("{key}")
    public void activateUser(@PathVariable Long key) {
        userActivationService.activateUserAccount(key);
    }
}
