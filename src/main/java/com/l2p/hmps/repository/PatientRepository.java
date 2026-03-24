package com.l2p.hmps.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.l2p.hmps.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    Optional<Patient> findByNhsId(String nhsId);

    boolean existsByNhsId(String nhsId);

    Optional<Patient> findByUser_Id(UUID userId);

    boolean existsByUser_Id(UUID userId);

    @Query("""
        SELECT p FROM Patient p
        WHERE LOWER(p.firstName) LIKE LOWER(CONCAT('%', :q, '%'))
           OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :q, '%'))
           OR LOWER(p.nhsId) LIKE LOWER(CONCAT('%', :q, '%'))
    """)
    Page<Patient> search(@Param("q") String q, Pageable pageable);

//    @Query("""
//        SELECT DISTINCT p FROM Patient p
//        JOIN Appointment a ON a.patient.id = p.id
//        WHERE a.doctor.id = :doctorId
//    """)
//    List<Patient> findPatientsForDoctor(@Param("doctorId") UUID doctorId);
}