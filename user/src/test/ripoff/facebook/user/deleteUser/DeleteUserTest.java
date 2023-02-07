package ripoff.facebook.user.deleteUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ripoff.facebook.clients.authentication.AuthClient;
import ripoff.facebook.clients.post.PostClient;
import ripoff.facebook.clients.relation.RelationClient;
import ripoff.facebook.clients.search.SearchClient;
import ripoff.facebook.user.commons.UserRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteUserTest {

    private DeleteUserService underTest;
    @Mock
    private RelationClient relationClient;
    @Mock
    private PostClient postClient;
    @Mock
    private AuthClient authenticationClient;
    @Mock
    private SearchClient searchClient;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        underTest = new DeleteUserService(
                relationClient,
                postClient,
                authenticationClient,
                searchClient,
                userRepository
        );
    }

    @Test
    public void shouldCallServicesToDelete(){
        //when
        underTest.deleteUser(any());
        //then
        verify(relationClient, times(1)).deleteUser(any());
        verify(postClient, times(1)).deleteAllPostsByUserId(any());
        verify(authenticationClient, times(1)).deleteUserAuthenticationData(any());
        verify(searchClient, times(1)).removeUser(any());
        verify(userRepository, times(1)).deleteById(any());
    }
}