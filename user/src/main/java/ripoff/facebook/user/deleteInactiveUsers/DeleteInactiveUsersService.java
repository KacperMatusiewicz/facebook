package ripoff.facebook.user.deleteInactiveUsers;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeleteInactiveUsersService {
    private final DeleteInactiveUsersRepository deleteInactiveUsersRepository;

    @Scheduled(fixedDelay = 60000)
    public void deleteInactiveUsers() {
        deleteInactiveUsersRepository.deleteInactiveUsers(LocalDateTime.now());
    }
}
