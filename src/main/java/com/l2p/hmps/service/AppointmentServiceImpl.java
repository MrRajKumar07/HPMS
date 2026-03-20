package com.l2p.hmps.service;

import com.l2p.hmps.dto.BookAppointmentRequest;
import com.l2p.hmps.exception.AppointmentException;
import com.l2p.hmps.mapper.AppointmentMapper;
import com.l2p.hmps.model.Appointment;
import com.l2p.hmps.model.AppointmentStatus;
import com.l2p.hmps.model.User;
import com.l2p.hmps.repository.AppointmentRepository;
import com.l2p.hmps.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    @Transactional
    public Appointment bookAppointment(BookAppointmentRequest request) {
        User patient = userRepository.findById(request.getPatientId())
                .orElseThrow(() -> new AppointmentException(
                        "Patient not found",
                        HttpStatus.NOT_FOUND,
                        "APPT_404"
                ));

        Appointment appointment = appointmentMapper.toEntity(request);
        appointment.setPatient(patient);

        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional(readOnly = true)
    public Appointment getAppointmentById(UUID appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException(
                        "Appointment not found",
                        HttpStatus.NOT_FOUND,
                        "APPT_404"
                ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> getAppointmentsByPatient(UUID patientId) {
        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new AppointmentException(
                        "Patient not found",
                        HttpStatus.NOT_FOUND,
                        "APPT_404"
                ));

        return appointmentRepository.findByPatient(patient);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<Appointment> getAppointmentsByDoctor(UUID doctorId) {
//        return appointmentRepository.findByDoctorId(doctorId);
//    }

    @Override
    @Transactional
    public Appointment cancelAppointment(UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException(
                        "Appointment not found",
                        HttpStatus.NOT_FOUND,
                        "APPT_404"
                ));

        appointment.setStatus(AppointmentStatus.CANCELLED);
        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public Appointment updateAppointmentStatus(UUID appointmentId, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException(
                        "Appointment not found",
                        HttpStatus.NOT_FOUND,
                        "APPT_404"
                ));

        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }
}