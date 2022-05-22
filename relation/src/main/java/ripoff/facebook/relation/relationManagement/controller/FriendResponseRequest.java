package ripoff.facebook.relation.relationManagement.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class FriendResponseRequest {
    Long targetId;
    ResponseType responseType;
}
