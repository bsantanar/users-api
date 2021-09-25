package bsantanar.com.usersapi.services;

import bsantanar.com.usersapi.models.Phone;
import bsantanar.com.usersapi.models.User;
import bsantanar.com.usersapi.repostories.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneServiceImpl phoneService;

    @Test
    void testFailedUpdatePhone(){
        User user = new User();
        Phone phone = new Phone();
        when(phoneRepository.save(any()))
                .thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            phoneService.updateUserPhone(phone, user);
        });
    }
}
