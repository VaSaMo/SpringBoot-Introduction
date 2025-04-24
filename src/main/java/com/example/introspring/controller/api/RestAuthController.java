package com.example.introspring.controller.api;

import com.example.introspring.dto.AuthRequest;
import com.example.introspring.dto.AuthResponse;
import com.example.introspring.security.CustomUserDetail;
import com.example.introspring.service.impl.CustomUserDetailsService;
import com.example.introspring.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
public class RestAuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

            String token = jwtService.generateToken(userDetails);
            AuthResponse authResponse = new AuthResponse(token);
            return ResponseEntity.status(200).body(authResponse);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }

    }

}
