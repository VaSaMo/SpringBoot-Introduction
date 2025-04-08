package com.example.introspring.service;


import com.example.introspring.entity.User;

public interface UserService {
    User findByEmail(String username);

    void createUser(User user);
}