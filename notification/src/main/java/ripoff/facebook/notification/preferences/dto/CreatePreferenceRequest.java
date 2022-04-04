package ripoff.facebook.notification.preferences.dto;

import lombok.*;
import ripoff.facebook.notification.preferences.NotificationEntry;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreatePreferenceRequest {
    private Long userId;
    private List<NotificationEntry> preferences;
    public Long getUserId() {
        return this.userId;
    }

}
