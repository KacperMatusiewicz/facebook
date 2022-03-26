package ripoff.facebook.post.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
