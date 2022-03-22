package ripoff.facebook.user.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailingService {

    MailConfig mailConfig;

    public MailingService() {
        this.mailConfig = MailConfig.getInstance();
    }

    public void sendConfirmationEmail(String email, String name) {

        String emailBody = "Hello, " + name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase() + "!, please confirm your account.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("confirmation.fb.ripoff@gmail.com");
        message.setTo(email);
        message.setSubject("Account activation");
        message.setText(emailBody);
        mailConfig.getJavaMailSender().send(message);

    }
}
