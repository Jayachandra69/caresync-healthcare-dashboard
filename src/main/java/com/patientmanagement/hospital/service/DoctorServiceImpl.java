package com.patientmanagement.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patientmanagement.hospital.model.Doctor;
import com.patientmanagement.hospital.repo.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    // =========================
    // Save Doctor
    // =========================

    @Override
    public Doctor saveDoctor(Doctor doctor) {

        return doctorRepository.save(doctor);

    }


    // =========================
    // Get All Doctors
    // =========================

    @Override
    public List<Doctor> getAllDoctors() {

        return doctorRepository.findAll();

    }


    // =========================
    // Get Doctor By ID
    // =========================

    @Override
    public Doctor getDoctorById(int doctorId) {

        return doctorRepository.findById(doctorId)
                .orElse(null);

    }


    // =========================
    // Update Doctor
    // =========================

    @Override
    public Doctor updateDoctor(int doctorId, Doctor doctor) {

        Doctor existingDoctor =
                doctorRepository.findById(doctorId)
                .orElse(null);


        if (existingDoctor != null) {

            existingDoctor.setDoctorName(
                    doctor.getDoctorName()
            );

            existingDoctor.setSpecialization(
                    doctor.getSpecialization()
            );

            existingDoctor.setExperience(
                    doctor.getExperience()
            );


            return doctorRepository.save(existingDoctor);

        }


        return null;

    }


    // =========================
    // Delete Doctor
    // =========================

    @Override
    public String deleteDoctor(int doctorId) {

        Doctor existingDoctor =
                doctorRepository.findById(doctorId)
                .orElse(null);


        if (existingDoctor != null) {

            doctorRepository.deleteById(doctorId);

            return "Deleted Doctor with "
                    + doctorId
                    + " Successfully !!";

        }


        return "Doctor with "
                + doctorId
                + " not found";

    }

}
