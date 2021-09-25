package bsantanar.com.usersapi.controllers;

import bsantanar.com.usersapi.dtos.UserRequestDto;
import bsantanar.com.usersapi.dtos.UserResponseDto;
import bsantanar.com.usersapi.models.User;
import bsantanar.com.usersapi.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto userRequest){

        User newUser = userService.createUser(userRequest);

        UserResponseDto userResponse = modelMapper.map(newUser, UserResponseDto.class);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

}
