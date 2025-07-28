package com.swiftpay.user_service.service;

import com.swiftpay.user_service.dto.UserDto;
import com.swiftpay.user_service.exception.UserAlreadyExistsException;
import com.swiftpay.user_service.model.User;
import com.swiftpay.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public User registerUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("User already exits");
        }
        User user =modelMapper.map(userDto, User.class);
        return userRepository.save(user);
    }
}
