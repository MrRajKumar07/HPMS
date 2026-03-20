package com.l2p.hmps.service;

import com.l2p.hmps.dto.BookAppointmentRequest;
import com.l2p.hmps.model.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    /**
     * Book a new appointment
     */
    Appointment bookAppointment(BookAppointmentRequest request);

    /**
     * ✅ Fetch appointment by ID
     */
    Appointment getAppointmentById(UUID appointmentId);

    /**
     * Fetch all appointments for a specific patient
     */
    List<Appointment> getAppointmentsByPatient(UUID patientId);

    /**
     * Fetch all appointments for a specific doctor
     */
    List<Appointment> getAppointmentsByDoctor(UUID doctorId);

    /**
     * Cancel an appointment by its ID
     */
    Appointment cancelAppointment(UUID appointmentId);

    /**
     * Update the status of an appointment
     */
    Appointment updateAppointmentStatus(UUID appointmentId, Appointment.AppointmentStatus status);
}