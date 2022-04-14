package ripoff.facebook.notification.preference.dto;

import lombok.*;
import ripoff.facebook.notification.preference.repository.NotificationPreferenceEntry;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreatePreferenceRequest {
    private Long userId;
    private List<NotificationPreferenceEntry> preferences;
    public Long getUserId() {
        return this.userId;
    }

}
