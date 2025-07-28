package com.swiftpay.user_service.controller;

import com.swiftpay.user_service.dto.UserDto;
import com.swiftpay.user_service.model.User;
import com.swiftpay.user_service.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto) {
        User user = userService.registerUser(userDto);
        UserDto userDto1 = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }
}
