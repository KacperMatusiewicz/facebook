package ripoff.facebook.relation.relationManagement.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ripoff.facebook.relation.relationManagement.controller.ResponseType;

@AllArgsConstructor
@Getter
public class FriendResponseDto {
    private final Long userId;
    private final Long targetId;
    private final ResponseType responseType;
}
