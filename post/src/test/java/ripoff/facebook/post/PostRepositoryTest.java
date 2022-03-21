package ripoff.facebook.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void shouldFindAllByUserId() {
        //given
        long userId = 10L;
        List<Post> expectedResultPosts = Arrays.asList(
                Post.builder()
                        .userId(userId)
                        .content("test1")
                        .creationDate(LocalDateTime.of(2005, Month.AUGUST, 12, 10, 10))
                        .attachmentPath(null)
                        .build(),
                Post.builder()
                        .userId(userId)
                        .content("test3")
                        .creationDate(LocalDateTime.now())
                        .attachmentPath(null)
                        .build()
        );
        List<Post> posts = new ArrayList<>();
        posts.add(expectedResultPosts.get(1));
        posts.add(expectedResultPosts.get(0));
        posts.add(
                Post.builder()
                        .userId(11L)
                        .content("test2")
                        .creationDate(LocalDateTime.of(2015, Month.AUGUST, 12, 10, 10))
                        .attachmentPath(null)
                        .build()
        );
        postRepository.saveAll(posts);
        //when
        List<Post> result = postRepository.findAllByUserId(userId);
        //then
        Assertions
                .assertThat(new HashSet<Post>(result))
                .usingRecursiveComparison()
                .isEqualTo(new HashSet<Post>(expectedResultPosts));
    }

    @Test
    void shouldNotFindAnyPosts() {
        //given
        List<Post> expectedResultPosts = new ArrayList<>();

        List<Post> posts = Arrays.asList(
                Post.builder()
                        .userId(10L)
                        .content("test1")
                        .creationDate(LocalDateTime.of(2005, Month.AUGUST, 12, 10, 10))
                        .attachmentPath(null)
                        .build(),
                Post.builder()
                        .userId(11L)
                        .content("test3")
                        .creationDate(LocalDateTime.now())
                        .attachmentPath(null)
                        .build()
        );

        postRepository.saveAll(posts);
        //when
        List<Post> result = postRepository.findAllByUserId(22L);
        //then
        Assertions
                .assertThat(new HashSet<Post>(result))
                .usingRecursiveComparison()
                .isEqualTo(new HashSet<Post>(expectedResultPosts));
    }
}