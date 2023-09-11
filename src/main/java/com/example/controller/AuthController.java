package com.example.controller;

import com.example.configuration.jwt.JwtRequest;
import com.example.configuration.jwt.JwtResponse;
import com.example.service.AuthService;
import com.example.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AuthController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthService authService;
@PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
     return authService.createJwtToken(jwtRequest);
    }
}
