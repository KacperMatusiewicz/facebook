package ripoff.facebook.post.createPost.controller;

import lombok.*;
import ripoff.facebook.post.createPost.service.dto.VisibilityGroupType;

import java.util.Set;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreationRequest {

    private String content;
    private VisibilityGroupType visibilityGroupType;
    private Set<Long> visibilityUsersId;
}
