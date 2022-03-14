package ripoff.facebook.post;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreationRequest {

    private Long userId;
    private String content;
}
