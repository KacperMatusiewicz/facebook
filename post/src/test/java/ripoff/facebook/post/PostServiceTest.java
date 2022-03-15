package ripoff.facebook.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    PostRepository postRepository;
    
    PostService service;
    
    @BeforeEach
    void setUp() {
        service = new PostService(postRepository);
    }

    @Test
    void shouldCallSaveWhenCreatingPost() {
        //given
        Post expectedPost = Post.builder()
                .userId(1L)
                .content("No siema")
                .creationDate(LocalDateTime.now())
                .attachmentPath(null)
                .build();

        PostCreationRequest req = PostCreationRequest.builder()
                .userId(1L)
                .content("No siema")
                .build();

        //when
        service.createPost(req);
        //then
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        Mockito.verify(postRepository).save(postCaptor.capture());
        Post capturedPost = postCaptor.getValue();
        assertThat(capturedPost).usingRecursiveComparison().ignoringFields("creationDate").isEqualTo(expectedPost);
    }

    @Test
    @Disabled
    void shouldAssignPresentTimeAtPostCreation() {
        // needs ClockConfiguration to be implemented for this
    }

    @Test
    void shouldCallGetAllPostByUserIdWhenGettingPostFromUser() {
        //given
        Long userId = 10L;
        //when
        service.getAllPostsByUser(userId);
        //then
        ArgumentCaptor<Long> userIdCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(postRepository).findAllByUserId(userIdCaptor.capture());
        Long capturedUserId = userIdCaptor.getValue();
        assertThat(capturedUserId).isEqualTo(userId);
    }
}