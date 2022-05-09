package ripoff.facebook.user.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "user_table")
@Setter
public class User {

    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private UserStatus userStatus;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    ActivationLink activationLink;
}
