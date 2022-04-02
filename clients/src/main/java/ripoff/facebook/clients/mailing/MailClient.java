package ripoff.facebook.clients.mailing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("mailing")
public interface MailClient {

    @PostMapping(path = "api/v1/mail")
    void sendEmail(@RequestBody MailDetails mailDetails);
}
