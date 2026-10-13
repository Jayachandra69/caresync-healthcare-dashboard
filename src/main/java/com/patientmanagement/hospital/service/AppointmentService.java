package com.patientmanagement.hospital.service;

import java.util.List;

import com.patientmanagement.hospital.model.Appointment;

public interface AppointmentService {

    Appointment saveAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(int appointmentId);

    Appointment updateAppointment(
            int appointmentId,
            Appointment appointment
    );

    String deleteAppointment(int appointmentId);
}