package ripoff.facebook.relation.userManagement;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/v1/relation/user")
@AllArgsConstructor
public class UserManagementController {

    UserManagementService service;

    @PostMapping("{id}")
    public void createUser(@PathVariable Long id) {
        log.info("Received request to create user with id: " + id);
        service.createUser(id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Received request to delete user with id: " + id);
        service.deleteUser(id);
    }
}
