package ripoff.facebook.relation.command;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ripoff.facebook.relation.entity.User;
import ripoff.facebook.relation.repository.GroupRepository;

@Service
@AllArgsConstructor
public class UserCreationService {

    GroupRepository repository;

    public void createUser(Long id) {
        repository.save(User.builder().id(id).build());
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
