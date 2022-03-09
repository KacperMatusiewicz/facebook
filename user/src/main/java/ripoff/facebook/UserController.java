package ripoff.facebook;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @PostMapping
    public void createUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.toString());
    }

}
