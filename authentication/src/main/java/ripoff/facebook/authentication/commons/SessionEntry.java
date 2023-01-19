package ripoff.facebook.authentication.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class SessionEntry {

    @Id
    private String sessionId;

    private Long userId;

    private LocalDateTime creationDate;
}
