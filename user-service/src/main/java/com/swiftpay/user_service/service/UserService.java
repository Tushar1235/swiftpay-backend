package com.swiftpay.user_service.service;

import com.swiftpay.user_service.dto.UserDto;
import com.swiftpay.user_service.exception.UserAlreadyExistsException;
import com.swiftpay.user_service.exception.UserNotFoundException;
import com.swiftpay.user_service.model.User;
import com.swiftpay.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public User registerUser(UserDto userDto) {
        logger.info("Registering user with email: {}",userDto.getEmail());
        if(userRepository.existsByEmail(userDto.getEmail())) {
            logger.error("Attempting to register duplicate email: {}",userDto.getEmail());
            throw new UserAlreadyExistsException("User already exits");
        }
        User user =modelMapper.map(userDto, User.class);
        logger.info("User registered successfully with id: {}",user.getId());
        return userRepository.save(user);
    }

    public UserDto getUser(String email) {
        logger.info("Fetching user by email: {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(()->{
            logger.error("User not found for email: {}",email);
            return new UserNotFoundException("User not found");
        });

        return modelMapper.map(user, UserDto.class);
    }
}
