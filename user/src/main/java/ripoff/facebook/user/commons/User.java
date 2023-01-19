package ripoff.facebook.user.commons;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime creationDate;
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    ActivationLink activationLink;
}
