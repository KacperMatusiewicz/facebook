package ripoff.facebook.user.updateUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.user.commons.BadUserDataException;
import ripoff.facebook.user.commons.User;
import ripoff.facebook.user.commons.UserRepository;
import ripoff.facebook.user.commons.UserValidationService;

@Service
@RequiredArgsConstructor
public class UpdateUserService {

    private final UserRepository repository;
    private final UserValidationService validationService;

    public void changePersonalInfo(PersonalInfoDto personalInfoDto) {
        validatePersonalInfo(personalInfoDto);
        updateUser(personalInfoDto);
    }

    public void changeContactInfo(ContactInfoDto contactInfoDto) {
        validateContactInfo(contactInfoDto);
        updateUser(contactInfoDto);
    }

    private void updateUser(ContactInfoDto contactInfoDto) {
        User user = repository.getById(contactInfoDto.getUserId());
        user.setEmail(contactInfoDto.getEmail());
        repository.save(user);
    }

    private void validateContactInfo(ContactInfoDto contactInfoDto) {
        if(!validationService.validateUserContactInfo(contactInfoDto)){
            throw new BadUserDataException("Bad user data.");
        }
    }

    private void updateUser(PersonalInfoDto personalInfoDto) {
        User user = repository.getById(personalInfoDto.getUserId());
        user.setName(personalInfoDto.getName());
        user.setLastName(personalInfoDto.getLastName());
        repository.save(user);
    }

    private void validatePersonalInfo(PersonalInfoDto personalInfoDto) {
        if(!validationService.validateUserPersonalInfo(personalInfoDto)) {
            throw new BadUserDataException("Bad user data.");
        }
    }
}
