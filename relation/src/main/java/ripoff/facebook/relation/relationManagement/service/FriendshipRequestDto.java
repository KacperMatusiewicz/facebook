package ripoff.facebook.relation.relationManagement.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FriendshipRequestDto {
    private final Long userId;
    private final Long targetId;
}
