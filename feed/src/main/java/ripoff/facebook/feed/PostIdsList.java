package ripoff.facebook.feed;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostIdsList {
    private List<Long> postIds;
}
