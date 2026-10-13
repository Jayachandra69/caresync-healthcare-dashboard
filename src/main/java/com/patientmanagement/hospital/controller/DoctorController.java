package com.patientmanagement.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientmanagement.hospital.model.Doctor;
import com.patientmanagement.hospital.service.DoctorService;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    // =========================
    // Add Doctor
    // =========================

//  http://localhost:1222/doctor/save
    @PostMapping("/save")
    public Doctor saveDoctor(@RequestBody Doctor doctor) {

        return doctorService.saveDoctor(doctor);

    }


    // =========================
    // Get All Doctors
    // =========================
    
//  http://localhost:1222/doctor/getAll
    @GetMapping("/getAll")
    public List<Doctor> getAllDoctors() {

        return doctorService.getAllDoctors();

    }


    // =========================
    // Get Doctor By ID
    // =========================

//  http://localhost:1222/doctor/getdoctor/1
    @GetMapping("/getdoctor/{doctorId}")
    public Doctor getDoctorById(
            @PathVariable int doctorId) {

        return doctorService.getDoctorById(doctorId);

    }


    // =========================
    // Update Doctor
    // =========================

//  http://localhost:1222/doctor/updatedoctor/1
    @PutMapping("/updatedoctor/{doctorId}")
    public Doctor updateDoctor(
            @PathVariable int doctorId,
            @RequestBody Doctor doctor) {

        return doctorService.updateDoctor(
                doctorId,
                doctor
        );

    }


    // =========================
    // Delete Doctor
    // =========================

//  http://localhost:1222/doctor/deletedoctor/1
    @DeleteMapping("/deletedoctor/{doctorId}")
    public String deleteDoctor(
            @PathVariable int doctorId) {

        return doctorService.deleteDoctor(doctorId);

    }

}