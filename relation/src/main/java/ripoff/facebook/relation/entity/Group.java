package ripoff.facebook.relation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Group {

    @Id
    @SequenceGenerator(
            name = "group_id_sequence",
            sequenceName = "group_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_id_sequence"
    )
    private Long groupId;

    @ManyToMany(mappedBy = "userGroups")
    private Set<GroupUser> users;
}

