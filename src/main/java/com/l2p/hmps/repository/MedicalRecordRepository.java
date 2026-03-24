package com.l2p.hmps.repository;

import com.l2p.hmps.model.Appointment;
import com.l2p.hmps.model.MedicalRecord;
import com.l2p.hmps.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {

    Page<MedicalRecord> findByPatientOrderByVisitDateDesc(User patient, Pageable pageable);

    Optional<MedicalRecord> findByAppointment(Appointment appointment);

    boolean existsByAppointment(Appointment appointment);
}