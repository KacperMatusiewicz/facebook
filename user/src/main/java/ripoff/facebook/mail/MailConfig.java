package ripoff.facebook.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


public class MailConfig {

    private JavaMailSenderImpl javaMailSender;
    private static MailConfig mailConfig;

    private MailConfig() {
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("confirmation.fb.ripoff@gmail.com");
        javaMailSender.setPassword("D@Dmin2022");
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }

    public synchronized static MailConfig getInstance() {
        if(mailConfig == null) {
            mailConfig = new MailConfig();
        }
        return mailConfig;

    }

    public JavaMailSenderImpl getJavaMailSender() {
        return javaMailSender;
    }

}
