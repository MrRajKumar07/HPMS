package com.l2p.hmps.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.l2p.hmps.dto.PatientDTO;

public interface PatientService {

    PatientDTO register(PatientDTO patientDTO);

    PatientDTO getByUserId(UUID userId);

    Page<PatientDTO> getAll(Pageable pageable);

    Page<PatientDTO> search(String q, Pageable pageable);

    PatientDTO update(UUID id, PatientDTO patientDTO);

    void delete(UUID id);
}
