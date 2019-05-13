package com.example.shop.service;

import com.example.shop.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);
    void login(String username, String password);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);
}
