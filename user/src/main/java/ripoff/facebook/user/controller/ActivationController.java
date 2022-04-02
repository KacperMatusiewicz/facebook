package ripoff.facebook.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.user.service.UserActivationService;

@RestController
@RequestMapping("api/v1/activate")
@AllArgsConstructor
public class ActivationController {

    UserActivationService userActivationService;

    @GetMapping("{key}")
    public void activateUser(@PathVariable Long key) {
        userActivationService.activateUserAccount(key);
    }
}
