package bsantanar.com.usersapi.services;

import bsantanar.com.usersapi.models.Phone;
import bsantanar.com.usersapi.models.User;
import bsantanar.com.usersapi.repostories.PhoneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{

    private final PhoneRepository phoneRepository;

    @Override
    public Phone updateUserPhone(Phone phone, User user) {
        try{
            phone.setUser(user);
            return phoneRepository.save(phone);
        } catch (Exception e){
            throw e;
        }
    }
}
