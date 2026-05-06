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

    public User register(User user) {
        //System.out.println("password = " + user.getPassword());
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new RuntimeException("手機號碼已存在");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User login(String phone, String password) {

        User user = userRepository.findByPhone(phone);

        if (user == null) {
            throw new RuntimeException("帳號不存在");
        }

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("密碼錯誤");
        }

        return user;
    }
}