package com.patientmanagement.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientmanagement.hospital.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor , Integer> {

}
