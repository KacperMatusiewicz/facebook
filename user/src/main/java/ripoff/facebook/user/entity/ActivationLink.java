package ripoff.facebook.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
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

    @OneToOne
    User user;

}
