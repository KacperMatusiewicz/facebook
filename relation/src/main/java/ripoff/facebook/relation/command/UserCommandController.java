package ripoff.facebook.relation.command;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/relation/user")
@AllArgsConstructor
public class UserCommandController {

    UserCreationService service;

    @PostMapping("{id}")
    public void createUser(@PathVariable Long id){
        service.createUser(id);
    }
}
