package ripoff.facebook.relation.command;

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
