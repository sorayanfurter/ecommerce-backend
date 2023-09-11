package com.example.service;

import com.example.configuration.jwt.JwtRequest;
import com.example.configuration.jwt.JwtResponse;
import com.example.configuration.jwt.JwtUtils;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();
        authenticate(username, password);
        if(userDetailsService.loadUserByUsername(jwtRequest.getUsername()) == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        String jwtToken = this.jwtUtils.generateAccessToken(username);

        UserEntity userEntity = userRepository.findByUsername(username).get();

        return new JwtResponse(userEntity, jwtToken);
    }

    private void authenticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){
            throw new Exception("User is disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad Credentials from user");
        }
    }
}
