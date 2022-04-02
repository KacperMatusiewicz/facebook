package ripoff.facebook.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ripoff.facebook.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("" +
            "select case when count(u) > 0 " +
            "then true else false end " +
            "from User u where u.email = ?1"
    )
    Boolean checkIfMailExists(String email);
}
