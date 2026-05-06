package com.example.socialmedia.service;

import com.example.socialmedia.entity.User;
import com.example.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 註冊
    public User register(User user) {

        // 手機重複檢查
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new RuntimeException("手機號碼已存在");
        }

        // 密碼加密
        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // 登入
    public User login(String phone, String password) {

        // ✔ 使用 Optional 正確寫法
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("帳號不存在"));

        // 密碼比對
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("密碼錯誤");
        }

        return user;
    }
}