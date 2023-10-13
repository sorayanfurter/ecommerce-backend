package com.example;

import com.example.entity.ERole;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
/*

	@Bean
	CommandLineRunner init(){
		return args -> {
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


			userRepository.save(userEntity);
			userRepository.save(userEntity2);

		};
	}*/
}
