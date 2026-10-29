
package com.hp.studentmanagement.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hp.studentmanagement.model.User;
import com.hp.studentmanagement.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (!userRepository.existsByUsername("admin")) {
                User admin = new User(
                        "admin",
                        passwordEncoder.encode("Admin@123"),
                        "ADMIN",
                        null
                );

                userRepository.save(admin);
            }

            if (!userRepository.existsByUsername("harshu")) {
                User studentUser = new User(
                        "harshu",
                        passwordEncoder.encode("Student@123"),
                        "STUDENT",
                        1L
                );

                userRepository.save(studentUser);
            }
        };
    }
}
