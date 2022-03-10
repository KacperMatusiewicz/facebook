package ripoff.facebook.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ripoff.facebook.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@Query("select u.email from User as u where exists(select u.email from User where u.email = ?1)")
    //@Query("select case when exists (select * from User where email = ?1) then cast(1 as bit) else cast(0 as bit) end")
    //@Query("select distinct u.email from User as u where u.email = ?1")
    @Query("" +
            "select case when count(u) > 0 " +
            "then true else false end " +
            "from User u where u.email = ?1"
    )
    Boolean checkIfMailExists(String email);
}
