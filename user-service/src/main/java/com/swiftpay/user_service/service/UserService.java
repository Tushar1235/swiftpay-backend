package com.swiftpay.user_service.service;

import com.swiftpay.user_service.dto.UserDto;
import com.swiftpay.user_service.model.User;
import com.swiftpay.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public User registerUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exits");
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }
}
