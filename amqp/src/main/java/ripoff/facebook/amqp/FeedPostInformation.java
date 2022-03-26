package ripoff.facebook.amqp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedPostInformation {
    private Long userId;
    private Long postId;
}
