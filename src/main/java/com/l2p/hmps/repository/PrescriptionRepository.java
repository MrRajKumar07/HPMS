package com.l2p.hmps.repository;

import com.l2p.hmps.model.Prescription;
import com.l2p.hmps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {

    List<Prescription> findByPatient(User patient);

    List<Prescription> findByPatientAndIsActiveTrue(User patient);

    boolean existsByIdAndDoctorId(UUID id, UUID doctorId);

    Optional<Prescription> findByIdAndDoctorId(UUID id, UUID doctorId);
}