package com.example.photoappusersservice.service;

import com.example.photoappusersservice.shared.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDetails);

    UserDto getUserDetailsByEmail(String username);

    UserDto getUserByUserId(String userId);
}
