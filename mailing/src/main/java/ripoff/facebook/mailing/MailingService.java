package ripoff.facebook.mailing;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.mailing.MailDetails;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailingService {

    private Session session;

    public void sendMail(MailDetails mailDetails) {

        try{

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("confirmation.fb.ripoff@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDetails.getRecipient()));
            message.setSubject(mailDetails.getSubject());
            message.setContent(mailDetails.getBody(), "text/html");
            Transport.send(message);

        } catch(Exception e) {
            System.out.println("Mail not sent.\nReason: "+e.getMessage());
        }
    }
}
