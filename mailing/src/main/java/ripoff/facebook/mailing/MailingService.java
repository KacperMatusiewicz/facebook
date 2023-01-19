package ripoff.facebook.mailing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.mailing.MailDetails;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailingService {

    @Value("${mail.address}")
    private String emailAddress;

    private final JavaMailSender mailSender;

    public void sendMail(MailDetails mailDetails) {

        try{
            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mailDetails.getRecipient().split("[,;]"));
                message.setFrom(emailAddress, "Ripoff Team");
                message.setSubject(mailDetails.getSubject());
                message.setText(mailDetails.getBody(), true);
            };
            mailSender.send(preparator);

            log.info("Mail successfully sent");
        } catch(Exception e) {
            log.error("Mail not sent.\nReason: "+e.getMessage());
        }
    }
}
