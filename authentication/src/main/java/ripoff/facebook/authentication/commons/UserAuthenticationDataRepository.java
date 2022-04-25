package ripoff.facebook.authentication.commons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ripoff.facebook.authentication.commons.UserAuthenticationData;

import java.util.Optional;

public interface UserAuthenticationDataRepository extends JpaRepository<UserAuthenticationData, Long> {

    @Query("select u from UserAuthenticationData u where u.login=?1")
    Optional<UserAuthenticationData> getByLogin(String login);
}
