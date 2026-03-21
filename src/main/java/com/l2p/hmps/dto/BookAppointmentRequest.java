package com.l2p.hmps.dto;

import com.l2p.hmps.model.AppointmentType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class BookAppointmentRequest {

    @NotNull(message = "Patient id is required")
    private UUID patientId;

    @NotNull(message = "Doctor id is required")
    private UUID doctorId;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date must be today or in the future")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;

    @NotBlank(message = "Slot is required")
    @Size(max = 100, message = "Slot cannot exceed 100 characters")
    private String slot;

    @NotNull(message = "Appointment type is required")
    private AppointmentType type;

    @Size(max = 255, message = "Reason cannot exceed 255 characters")
    private String reason;
}