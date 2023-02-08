package ripoff.facebook.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ripoff.facebook.post.commons.TimeService;
import ripoff.facebook.post.commons.repository.Post;
import ripoff.facebook.post.commons.repository.PostRepository;
import ripoff.facebook.post.createPost.controller.CreatePostController;
import ripoff.facebook.post.createPost.controller.PostCreationRequest;
import ripoff.facebook.post.createPost.service.BadPostDataException;
import ripoff.facebook.post.createPost.service.CreatePostService;
import ripoff.facebook.post.createPost.service.PostDataValidationService;
import ripoff.facebook.post.createPost.service.RelationService;
import ripoff.facebook.post.createPost.service.dto.PostCreationDto;
import ripoff.facebook.post.deletePost.DeletePostService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.assertj.core.api.InstanceOfAssertFactories.COLLECTION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;
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
    CreatePostService createPostService;
    DeletePostService service;
    
    @BeforeEach
    void setUp() {
        service = new DeletePostService(postRepository);

    }

    @Test
    void shouldCallSaveWhenCreatingPost() {
        //given
        given(timeService.getCurrentDateTime()).isEqualTo(
                LocalDateTime.of(2015, Month.DECEMBER, 12,12,12,12,999)
        );
        Post expectedPost = Post.builder()
                .userId(1L)
                .content("No siema")
                .creationDate(timeService.getCurrentDateTime())
                .attachmentPath(null)
                .build();


        Set<Long> userId = new HashSet<Long>();
        userId.add(1L);
        PostCreationRequest req = PostCreationRequest.builder()
                .visibilityUsersId(userId)
                .content("No siema")
                .build();
        //when
         new CreatePostController(createPostService).createPost(1L,req);
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
        postRepository.getById(userId);
        //then
        ArgumentCaptor<Long> userIdCaptor = ArgumentCaptor.forClass(Long.class);
        verify(postRepository).findAllByUserId(userIdCaptor.capture());
        Long capturedUserId = userIdCaptor.getValue();
        assertThat(capturedUserId).isEqualTo(userId);
    }


    @Test
    void shouldThrowBadPostDataExceptionWhenValidationFailed() {
        //given
        Set<Long> userId = new HashSet<Long>();
        userId.add(1L);
        PostCreationRequest req = PostCreationRequest.builder()
                .visibilityUsersId(userId)
                .content("No siema")
                .build();
        BDDMockito.given(validationService.validatePost(any())).willReturn(false);
        //when
        //then
        assertThatThrownBy(()-> new CreatePostController(createPostService).createPost(1L,req))
                .isInstanceOf(BadPostDataException.class)
                .hasMessageContaining("Post data is not correct.");
        verify(postRepository, never()).save(any());
    }


    @Test
    void shouldCallDataValidationServiceWhenCreatingNewPost() {
        //given
        Set<Long> userId = new HashSet<Long>();
        userId.add(1L);
        PostCreationRequest request = PostCreationRequest.builder().visibilityUsersId(userId).content("1234").build();
        PostCreationDto requestDot = PostCreationDto.builder().userId(1L).visibilityUsersId(userId).content("1234").build();
        BDDMockito.given(validationService.validatePost(any())).willReturn(true);
        //when
        new CreatePostController(createPostService).createPost(1L,request);
        //then
        ArgumentCaptor<PostCreationRequest> requestArgumentCaptor = ArgumentCaptor.forClass(PostCreationRequest.class);
        verify(validationService).validatePost(requestDot);
        PostCreationRequest capturedRequest = requestArgumentCaptor.getValue();
        assertThat(capturedRequest).usingRecursiveComparison().isEqualTo(request);
    }
}