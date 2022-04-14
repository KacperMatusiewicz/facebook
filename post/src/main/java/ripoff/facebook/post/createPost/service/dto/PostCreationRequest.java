package ripoff.facebook.post.createPost.service.dto;

import lombok.*;

import java.util.Set;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreationRequest {

    private Long userId;
    private String content;
    private VisibilityGroupType visibilityGroupType;
    private Long visibilityGroupId;
    private Set<Long> visibilityUsersId;
}
