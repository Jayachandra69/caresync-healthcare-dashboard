package com.patientmanagement.hospital.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patientmanagement.hospital.model.AppUser;
import com.patientmanagement.hospital.repo.AppUserRepository;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder) {

        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(String username, String password) {

        AppUser user = appUserRepository
                .findByUsername(username)
                .orElse(null);

        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(
                password,
                user.getPassword()
        );
    }
}