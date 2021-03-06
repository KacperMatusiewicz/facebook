package ripoff.facebook.relation.commons.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupRepository extends Neo4jRepository<User, Long> {

    @Query("match (u:User)-[:Follows]->(:User{id: $0}) return u")
    Optional<Set<User>> getFollowersByUserId(Long id);

    @Query("match (:User{id: $0})-[:Follows]->(u:User) return u")
    Optional<Set<User>> getFollowingsByUserId(Long id);

    @Query("match (friend:User)-[:Follows]->(user:User)-[:Follows]->(u) where user.id=$0 return friend")
    Optional<Set<User>> getFriendsByUserId(Long id);


    @Query("match (u1:User {id: $0}), (u2:User {id: $1}) merge (u1)-[:Follows]->(u2)")
    void createFollowRelation(Long followerId, Long targetId);

    @Query("match (:User {id: $0})-[f:Follows]->(:User {id: $1}) delete f")
    void deleteFollowRelation(Long followerId, Long targetId);

    // Friendship
    @Query("match (u1:User {id: $0}), (u2:User {id: $1}) merge (u1)-[:Friends]-(u2)")
    void createFriendshipRelation(Long followerId, Long targetId);

    @Query("match (:User {id: $0})-[f:Friends]-(:User {id: $1}) delete f")
    void deleteFriendshipRelation(Long followerId, Long targetId);
}
