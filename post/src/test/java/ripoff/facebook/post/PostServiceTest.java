package ripoff.facebook.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;
import ripoff.facebook.post.commons.TimeService;
import ripoff.facebook.post.commons.repository.Post;
import ripoff.facebook.post.commons.repository.PostRepository;
import ripoff.facebook.post.createPost.controller.PostCreationRequest;
import ripoff.facebook.post.createPost.service.BadPostDataException;
import ripoff.facebook.post.createPost.service.CreatePostService;
import ripoff.facebook.post.createPost.service.PostDataValidationService;
import ripoff.facebook.post.createPost.service.RelationService;
import ripoff.facebook.post.createPost.service.dto.PostCreationDto;
import ripoff.facebook.post.createPost.service.dto.VisibilityGroupType;
import ripoff.facebook.post.deletePost.DeletePostService;
import ripoff.facebook.post.getPost.GetPostService;
import ripoff.facebook.post.getPost.PostViewRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    PostRepository postRepository;
    @Mock
    TimeService timeService;
    @Mock
    PostDataValidationService validationService;
    @Mock
    AmqpTemplate template;
    @Mock
    RelationService relationService;

    @Mock
    private PostViewRepository postViewRepository;

    CreatePostService createPostService;

    GetPostService getPostService;
    
    @BeforeEach
    void setUp() {
        createPostService = new CreatePostService(postRepository, validationService, relationService, timeService, template);
        getPostService = new GetPostService(postViewRepository);
    }

    @Test
    void shouldCallSaveWhenCreatingPost() {
        //given
        given(validationService.validatePost(any())).willReturn(true);
        given(timeService.getCurrentDateTime()).willReturn(
                LocalDateTime.of(2015, Month.DECEMBER, 12,12,12,12,999)
        );
        Post expectedPost = Post.builder()
                .userId(1L)
                .content("No siema")
                .creationDate(timeService.getCurrentDateTime())
                .attachmentPath(null)
                .build();

        PostCreationDto req = PostCreationDto.builder()
                .userId(1L)
                .content("No siema")
                .visibilityGroupType(VisibilityGroupType.FRIENDS)
                .build();
        //when
        createPostService.createPost(req);
        //then
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postRepository).save(postCaptor.capture());
        Post capturedPost = postCaptor.getValue();
        assertThat(capturedPost).usingRecursiveComparison().isEqualTo(expectedPost);
    }

    @Test
    void shouldThrowBadPostDataExceptionWhenValidationFailed() {
        //given
        PostCreationDto req = PostCreationDto.builder()
                .userId(1L)
                .content("No siema")
                .visibilityGroupType(VisibilityGroupType.FRIENDS)
                .build();
        given(validationService.validatePost(req)).willReturn(false);
        //when
        //then
        assertThatThrownBy(()-> createPostService.createPost(req))
                .isInstanceOf(BadPostDataException.class)
                .hasMessageContaining("Post data is not correct.");
        verify(postRepository, never()).save(any());
    }

    @Test
    void shouldCallDataValidationServiceWhenCreatingNewPost() {
        //given
        PostCreationDto request = PostCreationDto.builder()
                .userId(1L)
                .content("1234")
                .visibilityGroupType(VisibilityGroupType.FRIENDS)
                .build();

        given(validationService.validatePost(any())).willReturn(true);
        given(relationService.getFriends(any())).willReturn(Set.of(1L, 2L));
        given(postRepository.save(any())).willReturn(new Post(1L, 2L, "asd", "asd", LocalDateTime.now()));

        //when
        createPostService.createPost(request);
        //then
        ArgumentCaptor<PostCreationDto> requestArgumentCaptor = ArgumentCaptor.forClass(PostCreationDto.class);
        verify(validationService).validatePost(requestArgumentCaptor.capture());
        PostCreationDto capturedRequest = requestArgumentCaptor.getValue();
        assertThat(capturedRequest).usingRecursiveComparison().isEqualTo(request);
    }
}