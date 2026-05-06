package com.example.socialmedia.controller;

import com.example.socialmedia.entity.User;
import com.example.socialmedia.service.UserService;
import com.example.socialmedia.dto.UserResponse;
import com.example.socialmedia.dto.LoginRequest;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // =========================
    // 註冊
    // =========================
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

    // =========================
    // JWT 登入
    // =========================
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new RuntimeException("帳號不存在"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("密碼錯誤");
        }

        String token = JwtUtil.generateToken(request.getPhone());

        return Map.of("token", token);
    }
}