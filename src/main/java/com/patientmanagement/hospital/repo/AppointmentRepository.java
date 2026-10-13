package com.patientmanagement.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientmanagement.hospital.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment , Integer> {

}
