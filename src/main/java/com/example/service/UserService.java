package com.example.service;

import com.example.entity.ERole;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {
    @Autowired
    UserRepository userEntityRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserEntity registerNewUser(UserEntity userEntity){

        RoleEntity role = roleRepository.findRoleByName(ERole.USER).get();
        Set<RoleEntity> userRoles = new HashSet<>();
        userRoles.add(role);
        userEntity.setRoles(userRoles);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return   userEntityRepository.save(userEntity);
    }
}
