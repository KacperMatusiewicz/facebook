package ripoff.facebook.amqp;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class FeedPostInformation {
    private Long userId;
    private Long postId;
}
