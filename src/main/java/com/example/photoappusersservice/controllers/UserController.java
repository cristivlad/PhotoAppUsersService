package com.example.photoappusersservice.controllers;

import com.example.photoappusersservice.model.CreateUserRequestModel;
import com.example.photoappusersservice.model.CreateUserResponseModel;
import com.example.photoappusersservice.model.UserResponseModel;
import com.example.photoappusersservice.service.UserService;
import com.example.photoappusersservice.shared.UserDto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Environment env;
    private final UserService userService;

    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "working on port: " + env.getProperty("local.server.port");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto user = userService.createUser(userDto);
        CreateUserResponseModel returnValue = modelMapper.map(user, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping("/user")
    public String testAuthenticated() {
        return "user authenticated";
    }

    @GetMapping("/{userId")
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {

        UserDto userDto = userService.getUserByUserId(userId);
        UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);
        return ok().body(returnValue);
    }
}
