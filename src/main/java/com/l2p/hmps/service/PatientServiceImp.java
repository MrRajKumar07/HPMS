package com.l2p.hmps.service;

import java.util.UUID;

import com.l2p.hmps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.l2p.hmps.dto.PatientDTO;
import com.l2p.hmps.exception.PatientException; // ✅ UPDATED
import com.l2p.hmps.mapper.PatientMapper;
import com.l2p.hmps.model.Patient;
import com.l2p.hmps.repository.PatientRepository;
import com.l2p.hmps.repository.UserRepository;

@Service
public class PatientServiceImp implements PatientService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public PatientDTO register(PatientDTO patientDTO) {

        Patient patient = patientMapper.toEntity(patientDTO);

        // 🔥 FETCH EXISTING USER (VERY IMPORTANT)
        User user = userRepository.findById(patientDTO.getUserId())
                .orElseThrow(() -> new PatientException(
                        "User not found with id: " + patientDTO.getUserId(),
                        HttpStatus.NOT_FOUND,
                        "USER_NOT_FOUND"
                ));

        // 🔥 SET USER
        patient.setUser(user);

        patient.setNhsId("NHS-" + System.currentTimeMillis());

        Patient savedPatient = patientRepository.save(patient);

        return patientMapper.toDTO(savedPatient);
    }
    // Get patient by userId
    @Override
    public PatientDTO getByUserId(UUID userId) {

        Patient patient = patientRepository.findByUser_Id(userId) // ✅ FIXED
                .orElseThrow(() -> new PatientException( // ✅ UPDATED
                        "Patient not found with userId: " + userId,
                        HttpStatus.NOT_FOUND,
                        "PATIENT_NOT_FOUND"
                ));

        return patientMapper.toDTO(patient);
    }

    // Get all patients
    @Override
    public Page<PatientDTO> getAll(Pageable pageable) {

        return patientRepository.findAll(pageable)
                .map(patientMapper::toDTO);
    }

    // Search patients
    @Override
    public Page<PatientDTO> search(String q, Pageable pageable) {

        return patientRepository.search(q, pageable) // ✅ USE SEARCH
                .map(patientMapper::toDTO);
    }

    // Update patient
    @Override
    public PatientDTO update(UUID id, PatientDTO patientDTO) {

        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientException( // ✅ UPDATED
                        "Patient not found with id: " + id,
                        HttpStatus.NOT_FOUND,
                        "PATIENT_NOT_FOUND"
                ));

        // Partial update
        if (patientDTO.getFirstName() != null)
            existingPatient.setFirstName(patientDTO.getFirstName());

        if (patientDTO.getLastName() != null)
            existingPatient.setLastName(patientDTO.getLastName());

        if (patientDTO.getDateOfBirth() != null)
            existingPatient.setDateOfBirth(patientDTO.getDateOfBirth());

        if (patientDTO.getGender() != null)
            existingPatient.setGender(patientDTO.getGender());

        if (patientDTO.getBloodGroup() != null)
            existingPatient.setBloodGroup(patientDTO.getBloodGroup());

        if (patientDTO.getPhone() != null)
            existingPatient.setPhone(patientDTO.getPhone());

        if (patientDTO.getAddress() != null)
            existingPatient.setAddress(patientDTO.getAddress());

        if (patientDTO.getEmergencyContact() != null)
            existingPatient.setEmergencyContact(patientDTO.getEmergencyContact());

        if (patientDTO.getInsuranceInfo() != null)
            existingPatient.setInsuranceInfo(patientDTO.getInsuranceInfo());

        Patient updatedPatient = patientRepository.save(existingPatient);

        return patientMapper.toDTO(updatedPatient);
    }

    // Delete patient (Soft delete recommended)
    @Override
    public void delete(UUID id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientException( // ✅ UPDATED
                        "Patient not found with id: " + id,
                        HttpStatus.NOT_FOUND,
                        "PATIENT_NOT_FOUND"
                ));

        // ✅ SOFT DELETE (better than delete)
        patient.setActive(false);
        patientRepository.save(patient);
    }
}