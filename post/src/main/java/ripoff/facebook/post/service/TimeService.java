package ripoff.facebook.post.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeService {

    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

}
