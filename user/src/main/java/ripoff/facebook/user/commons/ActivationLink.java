package ripoff.facebook.user.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ActivationLink {

    @Id
    @SequenceGenerator(
            name = "activation_id_sequence",
            sequenceName = "activation_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activation_id_sequence"
    )
    Long key;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    LocalDateTime creationDate;

    Boolean used;
}
