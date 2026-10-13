package com.patientmanagement.hospital.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patientmanagement.hospital.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

}