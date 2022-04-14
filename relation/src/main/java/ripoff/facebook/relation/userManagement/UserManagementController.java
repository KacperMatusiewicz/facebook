package ripoff.facebook.relation.userManagement;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/relation/user")
@AllArgsConstructor
public class UserManagementController {

    UserManagementService service;

    @PostMapping("{id}")
    public void createUser(@PathVariable Long id) {
        service.createUser(id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
