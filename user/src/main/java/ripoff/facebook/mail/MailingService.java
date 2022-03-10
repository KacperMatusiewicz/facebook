package ripoff.facebook.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailingService {

    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String email, String name) {

        String emailBody = "Hello, " + name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase() + "!, please confirm your account.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("confirmation.fb.ripoff@gmail.com");
        message.setTo(email);
        message.setSubject("Account activation");
        message.setText(emailBody);
        mailSender.send(message);

    }
}
