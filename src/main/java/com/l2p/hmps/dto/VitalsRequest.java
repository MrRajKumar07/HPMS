package com.l2p.hmps.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VitalsRequest {

    @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0")
    private Double weight;

    @DecimalMin(value = "0.0", inclusive = false, message = "Height must be greater than 0")
    private Double height;

    @DecimalMin(value = "0.0", inclusive = false, message = "Temperature must be greater than 0")
    private Double temperature;

    @Pattern(regexp = "^\\d{2,3}/\\d{2,3}$", message = "Blood pressure must be in format 120/80")
    private String bloodPressure;

    @Min(value = 20, message = "Heart rate must be at least 20")
    @Max(value = 300, message = "Heart rate must not exceed 300")
    private Integer heartRate;

    @Min(value = 50, message = "SpO2 must be at least 50")
    @Max(value = 100, message = "SpO2 must not exceed 100")
    private Integer spo2;
}