package ripoff.facebook.feed.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFeedPostRepository extends CrudRepository<UserFeedPost, String> {

    Optional<List<UserFeedPost>> findAllByUserId(Long userId);

}
