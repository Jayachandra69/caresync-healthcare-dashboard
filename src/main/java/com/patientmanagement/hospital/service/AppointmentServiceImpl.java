package com.patientmanagement.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patientmanagement.hospital.model.Appointment;
import com.patientmanagement.hospital.repo.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Override
    public Appointment saveAppointment(Appointment appointment) {

        return appointmentRepository.save(appointment);
    }


    @Override
    public List<Appointment> getAllAppointments() {

        return appointmentRepository.findAll();
    }


    @Override
    public Appointment getAppointmentById(int appointmentId) {

        return appointmentRepository
                .findById(appointmentId)
                .orElse(null);
    }


    @Override
    public Appointment updateAppointment(
            int appointmentId,
            Appointment appointment) {

        Appointment existingAppointment =
                appointmentRepository
                .findById(appointmentId)
                .orElse(null);


        if (existingAppointment != null) {

            existingAppointment.setPatientId(
                    appointment.getPatientId()
            );

            existingAppointment.setDoctorId(
                    appointment.getDoctorId()
            );

            existingAppointment.setAppointmentDate(
                    appointment.getAppointmentDate()
            );

            existingAppointment.setAppointmentTime(
                    appointment.getAppointmentTime()
            );

            existingAppointment.setReason(
                    appointment.getReason()
            );

            existingAppointment.setStatus(
                    appointment.getStatus()
            );


            return appointmentRepository
                    .save(existingAppointment);
        }


        return null;
    }


    @Override
    public String deleteAppointment(int appointmentId) {

        Appointment existingAppointment =
                appointmentRepository
                .findById(appointmentId)
                .orElse(null);


        if (existingAppointment != null) {

            appointmentRepository
                    .deleteById(appointmentId);

            return "Deleted Appointment with "
                    + appointmentId
                    + " Successfully !!";
        }


        return "Appointment with "
                + appointmentId
                + " not found";
    }
}
