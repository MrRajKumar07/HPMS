package com.l2p.hmps.repository;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.l2p.hmps.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    Optional<Patient> findByNhsId(String nhsId);

    boolean existsByNhsId(String nhsId);

    Optional<Patient> findByUserId(UUID userId);

}
