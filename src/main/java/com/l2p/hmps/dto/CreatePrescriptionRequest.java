package com.l2p.hmps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class CreatePrescriptionRequest {

    @NotNull(message = "Patient id is required")
    private UUID patientId;

    @NotNull(message = "Doctor id is required")
    private UUID doctorId;

    private UUID appointmentId;

    @NotBlank(message = "Medication name is required")
    @Size(max = 255, message = "Medication name cannot exceed 255 characters")
    private String medicationName;

    @NotBlank(message = "Dosage is required")
    @Size(max = 100, message = "Dosage cannot exceed 100 characters")
    private String dosage;

    @NotBlank(message = "Frequency is required")
    @Size(max = 100, message = "Frequency cannot exceed 100 characters")
    private String frequency;

    @NotBlank(message = "Duration is required")
    @Size(max = 100, message = "Duration cannot exceed 100 characters")
    private String duration;

    @Size(max = 2000, message = "Instructions cannot exceed 2000 characters")
    private String instructions;
}