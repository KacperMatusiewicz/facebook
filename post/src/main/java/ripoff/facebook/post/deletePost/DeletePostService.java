package ripoff.facebook.post.deletePost;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ripoff.facebook.post.commons.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class DeletePostService {
    private final PostRepository repository;

    @Transactional
    public void deleteAllPostsByUserId(Long userId) {
        repository.deleteAllByUserId(userId);
    }

}
