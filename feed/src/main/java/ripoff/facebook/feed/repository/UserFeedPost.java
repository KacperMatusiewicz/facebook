package ripoff.facebook.feed.repository;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("UserFeedPost")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserFeedPost {

    @Id
    String id;
    @Indexed
    Long userId;
    Long postId;

    public UserFeedPost(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
