package com.example.socialmedia.repository;

import com.example.socialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByPhone(String phone);

    boolean existsByPhone(String phone);
}