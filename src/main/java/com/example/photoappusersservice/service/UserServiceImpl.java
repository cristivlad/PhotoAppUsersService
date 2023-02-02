package com.example.photoappusersservice.service;

import com.example.photoappusersservice.model.User;
import com.example.photoappusersservice.repository.UserRepository;
import com.example.photoappusersservice.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(passwordEncoder.encode(userDetails.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User entity = modelMapper.map(userDetails, User.class);

        User savedUser = userRepository.save(entity);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserDetailsByEmail(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new ModelMapper().map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new UsernameNotFoundException(userId);

        return new ModelMapper().map(user, UserDto.class);
    }
}
