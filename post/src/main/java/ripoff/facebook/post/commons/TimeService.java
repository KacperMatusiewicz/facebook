package ripoff.facebook.post.commons;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class TimeService {

    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now(ZoneId.of("Europe/Warsaw"));
    }

}
