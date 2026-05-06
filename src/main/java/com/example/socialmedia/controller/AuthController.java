package com.example.socialmedia.controller;

import com.example.socialmedia.entity.User;
import com.example.socialmedia.service.UserService;
import com.example.socialmedia.dto.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.socialmedia.dto.LoginRequest;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody User user) {

        User saved = userService.register(user);

        UserResponse res = new UserResponse();
        res.setUserId(saved.getUserId());
        res.setUserName(saved.getUserName());
        res.setPhone(saved.getPhone());
        res.setEmail(saved.getEmail());

        return res;
    }
    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) {

        User user = userService.login(request.getPhone(), request.getPassword());

        UserResponse res = new UserResponse();
        res.setUserId(user.getUserId());
        res.setUserName(user.getUserName());
        res.setPhone(user.getPhone());
        res.setEmail(user.getEmail());

        return res;
    }
}