package ripoff.facebook.relation;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import ripoff.facebook.relation.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupRepository extends Neo4jRepository<User, Long> {

    @Query("match (u:User)-[:Follows]->(:User{id: $0}) return u")
    Optional<Set<User>> getFollowersByUserId(Long id);

}
