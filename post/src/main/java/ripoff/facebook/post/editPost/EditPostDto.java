package ripoff.facebook.post.editPost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EditPostDto {

    private final Long postId;
    private final Long userId;
    private final String content;
}
