package com.patientmanagement.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientmanagement.hospital.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Integer> {

}
