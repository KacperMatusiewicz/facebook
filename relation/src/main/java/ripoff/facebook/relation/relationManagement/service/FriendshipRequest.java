package ripoff.facebook.relation.relationManagement.service;

import lombok.*;

@AllArgsConstructor
@Data
@Getter
@NoArgsConstructor
@Setter
public class FriendshipRequest {
    private Long friend1;
    private Long friend2;
}
