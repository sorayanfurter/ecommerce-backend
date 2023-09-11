package com.example.configuration.jwt;

import com.example.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

@AllArgsConstructor
@Getter
@Setter

public class JwtResponse {

    private UserEntity userEntity;
    private String token;
}
