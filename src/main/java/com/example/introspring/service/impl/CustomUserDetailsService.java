package com.example.introspring.service.impl;


import com.example.introspring.security.CustomUserDetail;
import com.example.introspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.introspring.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        //Cómo hacer esto: User -> UserDetail
        return new CustomUserDetail(user);
    }



}