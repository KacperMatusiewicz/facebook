package ripoff.facebook.relation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ripoff.facebook.relation.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
