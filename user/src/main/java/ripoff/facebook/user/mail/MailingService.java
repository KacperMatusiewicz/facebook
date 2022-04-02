package ripoff.facebook.user.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@AllArgsConstructor
public class MailingService {

    MailConfig mailConfig;

    public MailingService() {
        this.mailConfig = MailConfig.getInstance();
    }

    public void sendConfirmationEmail(ConfirmationEmail confirmationEmail) {

        Properties properties = new Properties();
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.mime.charset", "UTF-8");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfig.getJavaMailSender().getUsername(), mailConfig.getJavaMailSender().getPassword());
            }
        });
        Message message = new MimeMessage(session);
        //SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom(new InternetAddress("confirmation.fb.ripoff@gmail.com"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(confirmationEmail.getRecipientAddress()));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setSubject("Account activation");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setContent(confirmationEmail.getEmailBody(), "text/html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
