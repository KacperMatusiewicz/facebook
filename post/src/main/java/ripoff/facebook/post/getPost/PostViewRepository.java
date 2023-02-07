package ripoff.facebook.post.getPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostViewRepository extends JpaRepository<PostView, Long> {
    List<PostView> findAllByUserId(Long userId);
}
