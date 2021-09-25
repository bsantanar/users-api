package bsantanar.com.usersapi.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PasswordServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PasswordServiceImpl passwordService;

    @Test
    void testGeneratePassword(){
        String password = "passowrd";
        String hashPassword = passwordService.generatePassword(password);
        assertThat(password).isNotEqualTo(hashPassword);
    }

}
