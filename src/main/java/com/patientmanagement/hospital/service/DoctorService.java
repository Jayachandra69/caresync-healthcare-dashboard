package com.patientmanagement.hospital.service;

import java.util.List;

import com.patientmanagement.hospital.model.Doctor;

public interface DoctorService {

    Doctor saveDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor getDoctorById(int doctorId);

    Doctor updateDoctor(int doctorId, Doctor doctor);

    String deleteDoctor(int doctorId);
}