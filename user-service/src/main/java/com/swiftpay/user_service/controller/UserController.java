package com.swiftpay.user_service.controller;

import com.swiftpay.user_service.dto.UserDto;
import com.swiftpay.user_service.model.User;
import com.swiftpay.user_service.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto) {
        logger.info("Received POST /users/register/");
        User user = userService.registerUser(userDto);
        UserDto userDto1 = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        logger.info("Received GET /users/email/{}", email);
        UserDto user = userService.getUser(email);
        logger.info("Returning user: {}", user);
        return ResponseEntity.ok(user);
    }
}
