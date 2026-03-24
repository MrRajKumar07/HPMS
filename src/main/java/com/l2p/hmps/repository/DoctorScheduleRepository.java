package com.l2p.hmps.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.l2p.hmps.model.DoctorSchedule;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, UUID> {
    List<DoctorSchedule> findByDoctor_Id(UUID doctorId);

    void deleteByDoctor_Id(UUID doctorId);
}