package ripoff.facebook.relation.command;

import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Data
@NoArgsConstructor
@Setter
public class FollowRequest {
    private Long followerId;
    private Long targetId;
}
