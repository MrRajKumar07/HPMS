package com.l2p.hmps.service;

import com.l2p.hmps.dto.BookAppointmentRequest;
import com.l2p.hmps.exception.AuthException;
import com.l2p.hmps.mapper.AppointmentMapper;
import com.l2p.hmps.model.Appointment;
import com.l2p.hmps.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    @Transactional
    public Appointment bookAppointment(BookAppointmentRequest request) {

        boolean slotTaken = appointmentRepository
                .existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                        request.getDoctorId(),
                        request.getAppointmentDate(),
                        request.getAppointmentTime()
                );

        if (slotTaken) {
            throw new AuthException(
                    "Appointment slot is already booked for this doctor.",
                    HttpStatus.CONFLICT,
                    "APPT_409"
            );
        }

        Appointment appointment = AppointmentMapper.toEntity(request);

        return appointmentRepository.save(appointment);
    }


    @Override
    @Transactional(readOnly = true)
    public Appointment getAppointmentById(UUID appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AuthException(
                        "Appointment not found",
                        HttpStatus.NOT_FOUND,
                        "APPT_404"
                ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> getAppointmentsByPatient(UUID patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }


    @Override

    @Transactional(readOnly = true)
    public List<Appointment> getAppointmentsByDoctor(UUID doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    /**
     * Cancel an appointment by ID
     */
    @Override
    @Transactional
    public Appointment cancelAppointment(UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AuthException(
                        "Appointment not found",
                        HttpStatus.NOT_FOUND,
                        "APPT_404"
                ));

        appointment.setStatus(Appointment.AppointmentStatus.CANCELLED);
        return appointmentRepository.save(appointment);
    }

    /**
     * Update appointment status
     */
    @Override
    @Transactional
    public Appointment updateAppointmentStatus(UUID appointmentId, Appointment.AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AuthException(
                        "Appointment not found",
                        HttpStatus.NOT_FOUND,
                        "APPT_404"
                ));

        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }
}