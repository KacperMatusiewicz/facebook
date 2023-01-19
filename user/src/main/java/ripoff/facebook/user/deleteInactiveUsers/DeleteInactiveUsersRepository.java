package ripoff.facebook.user.deleteInactiveUsers;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ripoff.facebook.user.commons.User;

import java.time.LocalDateTime;

@Repository
public interface DeleteInactiveUsersRepository extends org.springframework.data.repository.Repository<User,Long> {

    @Procedure("delete_inactive_users")
    void deleteInactiveUsers(LocalDateTime time);
}
