package com.l2p.hmps.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    private UUID id;

    @Column(name = "user_id", unique = true)
    private UUID userId;

    @Column(unique = true)
    private String nhsId;

    @NotBlank
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    private String lastName;

    @Past
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$")
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String emergencyContact;

    @Column(columnDefinition = "TEXT")
    private String insuranceInfo;

    private boolean isActive = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
