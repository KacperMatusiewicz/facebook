package ripoff.facebook.feed.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserFeedPostRepository {

    Optional<List<String>> findAllByUserId(Long userId);

    void addToBeginningOfUserFeed(Long userId, Long postId);

    Short checkUserFeedSize(Long userId);

    void removeFromEndOfUserFeed(Long userId, Short count);

    void removeUserFeed(Long userId);

}
