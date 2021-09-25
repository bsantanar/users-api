package bsantanar.com.usersapi.services;

import bsantanar.com.usersapi.dtos.UserRequestDto;
import bsantanar.com.usersapi.exception.EmailAlreadyExistsException;
import bsantanar.com.usersapi.models.User;
import bsantanar.com.usersapi.repostories.UserRepository;
import bsantanar.com.usersapi.security.JwtTokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PhoneService phoneService;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private PasswordService passwordService;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testFailedCreateUser(){
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("test@mail.com");
        userRequestDto.setName("test");
        userRequestDto.setPassword("Passwd12");

        User user = new User();
        user.setEmail("test@mail.com");
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(user));
        when(modelMapper.map(any(), any()))
                .thenReturn(user);
        Assertions.assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.createUser(userRequestDto);
        });
    }

    @Test
    void testFailedSaveUser(){
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("test@mail.com");
        userRequestDto.setName("test");
        userRequestDto.setPassword("Passwd12");

        User user = new User();
        user.setEmail("test@mail.com");
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(modelMapper.map(any(), any()))
                .thenReturn(user);
        when(passwordService.generatePassword(anyString()))
                .thenReturn("password");
        when(jwtTokenProvider.createToken(user))
                .thenReturn("token");
        User newUser = userService.createUser(userRequestDto);
        Assertions.assertTrue(newUser != null);
    }
}
