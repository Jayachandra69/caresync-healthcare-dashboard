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

import com.patientmanagement.hospital.model.Appointment;
import com.patientmanagement.hospital.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @PostMapping("/save")
    public Appointment saveAppointment(
            @RequestBody Appointment appointment) {

        return appointmentService
                .saveAppointment(appointment);
    }

//  http://localhost:1222/appointment/getAll
    @GetMapping("/getAll")
    public List<Appointment> getAllAppointments() {

        return appointmentService
                .getAllAppointments();
    }


    @GetMapping("/getappointment/{appointmentId}")
    public Appointment getAppointmentById(
            @PathVariable int appointmentId) {

        return appointmentService
                .getAppointmentById(appointmentId);
    }


    @PutMapping("/updateappointment/{appointmentId}")
    public Appointment updateAppointment(
            @PathVariable int appointmentId,
            @RequestBody Appointment appointment) {

        return appointmentService.updateAppointment(
                appointmentId,
                appointment
        );
    }


    @DeleteMapping("/deleteappointment/{appointmentId}")
    public String deleteAppointment(
            @PathVariable int appointmentId) {

        return appointmentService
                .deleteAppointment(appointmentId);
    }
}