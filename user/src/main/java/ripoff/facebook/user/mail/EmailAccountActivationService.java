package ripoff.facebook.user.mail;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.mailing.MailClient;
import ripoff.facebook.clients.mailing.MailDetails;

@Service
@AllArgsConstructor
public class EmailAccountActivationService implements EmailAccountActivation {

    MailClient mailClient;

    @Override
    public void sendActivationEmail(ActivationEmail activationEmail) {
        MailDetails mailDetails = MailDetails
                .builder()
                .recipient(activationEmail.getRecipientAddress())
                .body(activationEmail.getEmailBody())
                .subject("Account Activation")
                .build();
        mailClient.sendEmail(mailDetails);
    }
}
