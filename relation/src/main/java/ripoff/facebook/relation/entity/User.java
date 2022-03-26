package ripoff.facebook.relation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node("User")
@AllArgsConstructor
@Builder
@Getter
public class User {

    @Id
    private final Long id;

    @Relationship(type = "Follows", direction = Relationship.Direction.INCOMING)
    private Set<User> followers;
}
