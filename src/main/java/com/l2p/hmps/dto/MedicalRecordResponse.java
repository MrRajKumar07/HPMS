package com.l2p.hmps.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class MedicalRecordResponse {
    private UUID id;
    private UUID patientId;
    private UUID doctorId;
    private UUID appointmentId;
    private LocalDate visitDate;
    private String chiefComplaint;
    private String diagnosis;
    private String icd10Code;
    private String symptoms;
    private String treatmentPlan;
    private LocalDate followUpDate;
    private Double weight;
    private Double height;
    private Double temperature;
    private String bloodPressure;
    private Integer heartRate;
    private Integer spo2;
}