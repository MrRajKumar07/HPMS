package com.l2p.hmps.service;

import java.util.UUID;

import com.l2p.hmps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.l2p.hmps.dto.PatientDTO;
import com.l2p.hmps.exception.PatientException;
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
        User user = userRepository.findById(patientDTO.getUserId())
                .orElseThrow(() -> new PatientException("User not found with id: " + patientDTO.getUserId(), HttpStatus.NOT_FOUND, "PATIENT_404"));
        patient.setUser(user);
        patient.setNhsId("NHS-" + System.currentTimeMillis());
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedPatient);
    }

    @Override
    public PatientDTO getByUserId(UUID userId) {
        Patient patient = patientRepository.findByUser_Id(userId)
                .orElseThrow(() -> new PatientException("Patient not found with userId: " + userId, HttpStatus.NOT_FOUND, "PATIENT_404"));
        return patientMapper.toDTO(patient);
    }

    @Override
    public Page<PatientDTO> getAll(Pageable pageable) {
        return patientRepository.findAll(pageable)
                .map(patientMapper::toDTO);
    }

    @Override
    public Page<PatientDTO> search(String q, Pageable pageable) {
        return patientRepository.search(q, pageable)
                .map(patientMapper::toDTO);
    }

    @Override
    public PatientDTO update(UUID id, PatientDTO patientDTO) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientException("Patient not found with id: " + id, HttpStatus.NOT_FOUND, "PATIENT_404"));

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

    @Override
    public void delete(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientException("Patient not found with id: " + id, HttpStatus.NOT_FOUND, "PATIENT_404"));
        patient.setActive(false);
        patientRepository.save(patient);
    }
}