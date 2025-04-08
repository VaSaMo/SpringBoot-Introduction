package com.example.introspring.service.impl;

import com.example.introspring.entity.Course;
import com.example.introspring.entity.User;
import com.example.introspring.repository.UserRepository;
import com.example.introspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username).orElseThrow();
    }


    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Password should be encoded in the controller or service layer
        userRepository.save(user);
    }
}
