package ripoff.facebook.relation;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateNewGroupRequest {
    List<Long> userIds;
}
