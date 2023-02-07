package ripoff.facebook.authentication.checkCrendentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthenticationDataViewRepository extends JpaRepository<UserAuthenticationDataView, Long> {
}
