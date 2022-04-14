package ripoff.facebook.relation.userManagement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.relation.commons.repository.GroupRepository;
import ripoff.facebook.relation.commons.repository.User;

@Service
@AllArgsConstructor
public class UserManagementService {

    GroupRepository repository;

    public void createUser(Long id) {
        repository.save(User.builder().id(id).build());
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
