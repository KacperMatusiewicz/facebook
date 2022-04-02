package ripoff.facebook.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ripoff.facebook.user.entity.ActivationLink;

@Repository
public interface ActivationRepository extends JpaRepository<ActivationLink, Long> {

}
