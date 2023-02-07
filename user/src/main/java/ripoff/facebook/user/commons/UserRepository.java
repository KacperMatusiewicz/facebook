package ripoff.facebook.user.commons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("" +
//            "select case when count(u) > 0 " +
//            "then true else false end " +
//            "from User u where u.email = ?1"
//    )
    @Query(value = "select exists (select 1 from user_email_view u where u.email=:email)", nativeQuery = true)
    Boolean checkIfMailExists(@Param("email") String email);


    Optional<User> findByEmail(String email);
}
