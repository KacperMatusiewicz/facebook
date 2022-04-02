package ripoff.facebook.clients.mailing;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Builder
public class MailDetails {

    private String recipient;
    private String subject;
    private String body;
}
