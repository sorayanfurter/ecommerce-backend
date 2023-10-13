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
/*
     public void initRoleAndUser(){
        UserEntity userEntity = UserEntity.builder()
                .email("anyi@mail.com")
                .username("anyi")
                .firstname("Anyi")
                .lastname("Perez")
                .password(passwordEncoder.encode("1234"))
                .roles(Set.of(RoleEntity.builder()
                        .name(ERole.valueOf(ERole.ADMIN.name())).build()))
                .build();
        UserEntity userEntity2 = UserEntity.builder()
                .email("santiago@mail.com")
                .username("santiago")
                .firstname("Santiago")
                .lastname("Perez")
                .password(passwordEncoder.encode("1234"))
                .roles(Set.of(RoleEntity.builder()
                        .name(ERole.valueOf(ERole.USER.name())).build()))
                .build();


        userEntityRepository.save(userEntity);
        userEntityRepository.save(userEntity2);


    }


    public UserEntity registerNewUser(UserEntity userEntity){

        RoleEntity role = roleRepository.findRoleByName(ERole.USER).get();
        Set<RoleEntity> userRoles = new HashSet<>();
        userRoles.add(role);
        userEntity.setRoles(userRoles);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return   userEntityRepository.save(userEntity);
    }*/

    public UserEntity  registerNewUser(UserEntity userEntity){
        RoleEntity role = roleRepository.findRoleByName(ERole.USER).get();
        Set<RoleEntity> roleSet = new HashSet<>();
        roleSet.add(role);
        userEntity.setRoles(roleSet);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

       return userEntityRepository.save(userEntity);
    }


}
