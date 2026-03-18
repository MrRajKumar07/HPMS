package com.l2p.hmps.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.l2p.hmps.dto.PatientDTO;

public interface PatientService {

    // Register new patient
    PatientDTO register(PatientDTO patientDTO);

    // Get patient by userId
    PatientDTO getByUserId(UUID userId);

    // Get all patients (paginated)
    Page<PatientDTO> getAll(Pageable pageable);

    // Search patients
    Page<PatientDTO> search(String q, Pageable pageable);

    // Update patient
    PatientDTO update(UUID id, PatientDTO patientDTO);

    // Delete patient (soft delete)
    void delete(UUID id);
}