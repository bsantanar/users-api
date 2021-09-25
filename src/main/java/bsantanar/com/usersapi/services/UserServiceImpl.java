package bsantanar.com.usersapi.services;

import bsantanar.com.usersapi.dtos.UserRequestDto;
import bsantanar.com.usersapi.exception.EmailAlreadyExistsException;
import bsantanar.com.usersapi.models.Phone;
import bsantanar.com.usersapi.models.User;
import bsantanar.com.usersapi.repostories.UserRepository;
import bsantanar.com.usersapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PhoneService phoneService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordService passwordService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public User createUser(UserRequestDto userRequestDto) {
        try{
            User user = modelMapper.map(userRequestDto, User.class);
            Optional<User> userExists = userRepository.findByEmail(user.getEmail());
            //if email exists throw exception
            if(userExists.isPresent()){
                throw new EmailAlreadyExistsException();
            }
            //hash password
            user.setPassword(passwordService.generatePassword(userRequestDto.getPassword()));
            //persist jwt
            String token = jwtTokenProvider.createToken(user);
            user.setToken(token);
            userRepository.save(user);

            //persist user on phones entities
            if(user.getPhones() != null && !user.getPhones().isEmpty())
                user.getPhones().stream()
                        .map(phone -> phoneService.updateUserPhone(phone, user))
                        .collect(Collectors.toList());
            return user;
        } catch (Exception e){
            throw e;
        }
    }
}
