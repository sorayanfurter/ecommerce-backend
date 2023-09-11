package com.example.controller;

import com.example.entity.UserEntity;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping({"/registerNewUser"})
    public UserEntity registerNewUser(@RequestBody UserEntity userEntity){
        return userService.registerNewUser(userEntity);
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin() {
        return "This URL is only accesible to Admin";
    }

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('USER')")
    public String forUser(){
        return "This URL is only accesible to the User";
    }
}
