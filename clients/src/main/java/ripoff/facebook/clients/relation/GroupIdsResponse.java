package ripoff.facebook.clients.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GroupIdsResponse {
    private Set<Long> groupIds;
}
