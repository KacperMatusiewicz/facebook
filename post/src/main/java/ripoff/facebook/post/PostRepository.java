package ripoff.facebook.post;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.userId = ?1")
    List<Post> findAllByUserId(Long userId);

    @Modifying
    @Query("delete from Post p where p.userId = ?1")
    void deleteAllByUserId(Long userId);
}
