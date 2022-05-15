package ripoff.facebook.post.editPost;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {

    private Long postId;
    private String content;
}
