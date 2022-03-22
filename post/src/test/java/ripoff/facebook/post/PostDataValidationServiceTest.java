package ripoff.facebook.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ripoff.facebook.clients.user.UserClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostDataValidationServiceTest {

    @Mock
    RestTemplate restTemplate;
    @Mock
    UserClient userClient;
    PostDataValidationService service;

    @BeforeEach
    void setUp() {
        service = new PostDataValidationService(userClient,restTemplate);
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
        Mockito
                .when(restTemplate.getForObject("localhost:8080/api/v1/user/"+userId, Boolean.class))
                .thenReturn(true);
        //when
        boolean result = service.validateUserId(userId);
        //then
        assertTrue(result);
    }
    @Test
    void shouldNotValidateUserId() {
        //given
        Long userId = 123L;
        Mockito
                .when(
                        restTemplate.getForObject("localhost:8080/api/v1/user/"+userId, Boolean.class)
                )
                .thenReturn(Boolean.FALSE);

        //when
        boolean result = service.validateUserId(userId);
        //then
        assertFalse(result);
    }
    @Test
    void shouldThrowResponseStatusException() {
        //given
        Long userId = 123L;
        Mockito
                .when(restTemplate.getForObject("localhost:8080/api/v1/user/"+userId, Boolean.class))
                .thenReturn(null);
        //when
        //then
        assertThatThrownBy(
                ()-> service.validateUserId(userId))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Could not connect to the User service");
    }
}