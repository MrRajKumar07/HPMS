package com.l2p.hmps.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PrescriptionResponse {

    private UUID id;
    private UUID patientId;
    private UUID doctorId;
    private UUID appointmentId;
    private String medicationName;
    private String dosage;
    private String frequency;
    private String duration;
    private String instructions;
    private boolean isActive;
    private boolean isFilled;
    private UUID filledBy;
    private LocalDateTime filledAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}