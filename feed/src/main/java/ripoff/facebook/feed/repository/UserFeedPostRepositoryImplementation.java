package ripoff.facebook.feed.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserFeedPostRepositoryImplementation implements UserFeedPostRepository{

    private final StringRedisTemplate stringRedisTemplate;
    private final int MAX_FEED_SIZE = 500;

    @Override
    public Optional<List<String>> findAllByUserId(Long userId) {
        return Optional.ofNullable(stringRedisTemplate.opsForList().range(userId.toString(), 0, -1));
    }

    @Override
    public void addToBeginningOfUserFeed(Long userId, Long postId) {
        int userFeedSize = checkUserFeedSize(userId);
        if(userFeedSize > MAX_FEED_SIZE){
                    removeFromEndOfUserFeed(userId, (short) (userFeedSize - MAX_FEED_SIZE));
        }

        stringRedisTemplate.opsForList().leftPush(userId.toString(), postId.toString());
    }

    @Override
    public Short checkUserFeedSize(Long userId) {
        return stringRedisTemplate.opsForList().size(userId.toString()).shortValue();
    }

    @Override
    public void removeFromEndOfUserFeed(Long userId, Short count) {
        stringRedisTemplate.opsForList().rightPop(userId.toString(), count);
    }

    @Override
    public void removeUserFeed(Long userId) {
        stringRedisTemplate.delete(userId.toString());
    }
}
