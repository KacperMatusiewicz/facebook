package ripoff.facebook.mailing;

import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Configuration
public class MailingConfig {

    @Bean
    public Session session() {
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
                return new PasswordAuthentication("confirmation.fb.ripoff@gmail.com", "D@Dmin2022");
            }
        });
        return session;
    }

}
