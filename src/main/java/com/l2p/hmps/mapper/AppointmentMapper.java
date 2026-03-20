package com.l2p.hmps.mapper;

import com.l2p.hmps.dto.BookAppointmentRequest;
import com.l2p.hmps.model.Appointment;

public class AppointmentMapper {

    // Convert DTO to Entity
    public static Appointment toEntity(BookAppointmentRequest request) {
        Appointment appointment = new Appointment();
        appointment.setPatientId(request.getPatientId());
        appointment.setDoctorId(request.getDoctorId());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setType(request.getType());
        appointment.setReason(request.getReason());
        return appointment;
    }

    // Convert Entity to DTO
    public static BookAppointmentRequest toDto(Appointment appointment) {
        BookAppointmentRequest dto = new BookAppointmentRequest();
        dto.setPatientId(appointment.getPatientId());
        dto.setDoctorId(appointment.getDoctorId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setAppointmentTime(appointment.getAppointmentTime());
        dto.setType(appointment.getType());
        dto.setReason(appointment.getReason());
        return dto;
    }
}