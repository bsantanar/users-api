package bsantanar.com.usersapi.services;

import bsantanar.com.usersapi.dtos.UserRequestDto;
import bsantanar.com.usersapi.models.User;

public interface UserService {

    User createUser(UserRequestDto userRequestDto);

}
