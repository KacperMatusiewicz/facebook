package ripoff.facebook.post;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
/*
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
    DeletePostService service;
    
    @BeforeEach
    void setUp() {
        service = new DeletePostService(postRepository, relationService, timeService, validationService, template);
    }

    @Test
    void shouldCallSaveWhenCreatingPost() {
        //given
        given(timeService.getCurrentDateTime()).willReturn(
                LocalDateTime.of(2015, Month.DECEMBER, 12,12,12,12,999)
        );
        Post expectedPost = Post.builder()
                .userId(1L)
                .content("No siema")
                .creationDate(timeService.getCurrentDateTime())
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
        verify(postRepository).save(postCaptor.capture());
        Post capturedPost = postCaptor.getValue();
        assertThat(capturedPost).usingRecursiveComparison().isEqualTo(expectedPost);
    }

    @Test
    void shouldCallGetAllPostByUserIdWhenGettingPostFromUser() {
        //given
        Long userId = 10L;
        //when
        service.getAllPostsByUser(userId);
        //then
        ArgumentCaptor<Long> userIdCaptor = ArgumentCaptor.forClass(Long.class);
        verify(postRepository).findAllByUserId(userIdCaptor.capture());
        Long capturedUserId = userIdCaptor.getValue();
        assertThat(capturedUserId).isEqualTo(userId);
    }

    @Test
    void shouldThrowBadPostDataExceptionWhenValidationFailed() {
        //given
        PostCreationRequest req = PostCreationRequest.builder()
                .userId(1L)
                .content("No siema")
                .build();
        given(validationService.validatePost(req)).willReturn(false);
        //when
        //then
        assertThatThrownBy(()-> service.createPost(req))
                .isInstanceOf(BadPostDataException.class)
                .hasMessageContaining("Post data is not correct.");
        verify(postRepository, never()).save(any());
    }

    @Test
    void shouldCallDataValidationServiceWhenCreatingNewPost() {
        //given
        PostCreationRequest request = PostCreationRequest.builder().userId(1L).content("1234").build();
        given(validationService.validatePost(any())).willReturn(true);
        //when
        service.createPost(request);
        //then
        ArgumentCaptor<PostCreationRequest> requestArgumentCaptor = ArgumentCaptor.forClass(PostCreationRequest.class);
        verify(validationService).validatePost(requestArgumentCaptor.capture());
        PostCreationRequest capturedRequest = requestArgumentCaptor.getValue();
        assertThat(capturedRequest).usingRecursiveComparison().isEqualTo(request);
    }
 */
}