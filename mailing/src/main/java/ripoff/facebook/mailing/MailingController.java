package ripoff.facebook.mailing;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.clients.mailing.MailDetails;
@Slf4j
@RestController
@RequestMapping("api/v1/mail")
@AllArgsConstructor
public class MailingController {

    private MailingService service;

    @PostMapping
    public void sendEmail(@RequestBody MailDetails mailDetails) {
        log.info("Received send email request.");
        service.sendMail(mailDetails);
    }

}
