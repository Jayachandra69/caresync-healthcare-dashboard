package com.patientmanagement.hospital.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.patientmanagement.hospital.model.AppUser;
import com.patientmanagement.hospital.repo.AppUserRepository;

@Configuration
public class AdminUserInitializer {

    @Bean
    public ApplicationRunner initializeAdmin(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.username}") String username,
            @Value("${app.admin.password}") String password) {

        return args -> {

            if (appUserRepository.findByUsername(username).isEmpty()) {

                AppUser admin = new AppUser();

                admin.setUsername(username);
                admin.setPassword(passwordEncoder.encode(password));
                admin.setRole("ADMIN");

                appUserRepository.save(admin);

                System.out.println(
                        "Administrator account created successfully!"
                );

            } else {
                System.out.println(
                        "Administrator account already exists."
                );
            }
        };
    }
}