package ripoff.facebook.post.getPost;

import lombok.Data;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@Immutable
public class PostView {
    @Id
    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime creationDate;
}
