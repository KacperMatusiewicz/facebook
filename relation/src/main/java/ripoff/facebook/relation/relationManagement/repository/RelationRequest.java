package ripoff.facebook.relation.relationManagement.repository;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class RelationRequest {

    @Id
    @SequenceGenerator(
            name = "relation_request_id_sequence",
            sequenceName = "relation_request_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "relation_request_id_sequence"
    )
    private Long id;

    private Long requesterId;
    private Long recipientId;
    private RelationRequestType requestType;
    private RelationRequestStatus requestStatus;
}
