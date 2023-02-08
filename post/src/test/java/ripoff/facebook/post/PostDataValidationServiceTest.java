package ripoff.facebook.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ripoff.facebook.clients.user.UserClient;
import ripoff.facebook.clients.user.UserExistsResponse;
import ripoff.facebook.post.createPost.service.PostDataValidationService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostDataValidationServiceTest {

    @Mock
    RestTemplate restTemplate;
    @Mock
    UserClient userClient;
    PostDataValidationService service;

    @BeforeEach
    void setUp() {
        service = new PostDataValidationService(userClient, restTemplate);
    }

    @Test
    void shouldNotValidatePostContentLength() {
        //given
        StringBuilder sb = new StringBuilder();
        sb.setLength(512 + 1);
        String postContent = sb.toString();
        //when
        boolean result = service.validateContent(postContent);
        //then
        assertFalse(result);
    }

    @Test
    void shouldValidatePostContentLength() {
        //given
        String postContent = "abc";
        //when
        boolean result = service.validateContent(postContent);
        //then
        assertTrue(result);
    }

    @Test
    void shouldValidateUserId() {
        //given
        Long userId = 123L;
        given(userClient.checkIfUserExistsById(any())).willReturn(new UserExistsResponse(true));
        //when
        boolean result = service.validateUserId(userId);
        //then
        assertTrue(result);
    }

    @Test
    void shouldNotValidateUserId() {
        //given
        Long userId = 123L;
        given(userClient.checkIfUserExistsById(any())).willReturn(new UserExistsResponse(false));
        //when
        boolean result = service.validateUserId(userId);
        //then
        assertFalse(result);
    }
}