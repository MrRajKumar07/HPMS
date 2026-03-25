package com.l2p.hmps.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.l2p.hmps.dto.DepartmentDTO;
import com.l2p.hmps.dto.DoctorDTO;
import com.l2p.hmps.dto.DoctorScheduleDTO;

public interface DoctorService {

    // Register doctor (creates user + doctor)
    DoctorDTO register(DoctorDTO doctorDTO);    // Get all doctors with pagination\n    Page<DoctorDTO> getAll(Pageable pageable);\n\n    // Get doctor by ID\n    DoctorDTO getById(UUID id);

    // Get available slots for a doctor on a specific date
    List<String> getAvailableSlots(UUID doctorId, LocalDate date);

    // Set doctor schedule
    void setSchedule(UUID doctorId, List<DoctorScheduleDTO> schedules);

    // Dashboard data
    Object getDashboard(UUID doctorId);

    // Assign head doctor
    DepartmentDTO assignHeadDoctor(UUID deptId, UUID doctorId);

    // Get all doctors in a department
    List<DoctorDTO> getDoctors(UUID deptId);
}
