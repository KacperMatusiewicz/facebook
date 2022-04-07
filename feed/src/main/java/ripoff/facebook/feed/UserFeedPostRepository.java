package ripoff.facebook.feed;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFeedPostRepository extends CrudRepository<UserFeedPost, String> {

    List<UserFeedPost> findAllByUserId(Long userId);

}
