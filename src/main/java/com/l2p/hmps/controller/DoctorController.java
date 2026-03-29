package com.l2p.hmps.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.l2p.hmps.dto.ApiResponse;
import com.l2p.hmps.dto.DoctorDTO;
import com.l2p.hmps.dto.DoctorScheduleDTO;
import com.l2p.hmps.dto.DepartmentDTO;
import com.l2p.hmps.service.DoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // ================= CREATE DOCTOR =================
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DoctorDTO>> register(@Valid @RequestBody DoctorDTO dto) {
        DoctorDTO doctor = doctorService.register(dto);
        return new ResponseEntity<>(
                ApiResponse.success("Doctor registered successfully", doctor),
                HttpStatus.CREATED
        );
    }

    // ================= AVAILABLE SLOTS =================
    @GetMapping("/{id}/slots")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<String>>> getSlots(
            @PathVariable UUID id,
            @RequestParam LocalDate date) {

        List<String> slots = doctorService.getAvailableSlots(id, date);

        return ResponseEntity.ok(
                ApiResponse.success("Slots fetched successfully", slots)
        );
    }

    // ================= DASHBOARD =================
    @GetMapping("/{id}/dashboard")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<ApiResponse<Object>> getDashboard(@PathVariable UUID id) {

        Object dashboard = doctorService.getDashboard(id);

        return ResponseEntity.ok(
                ApiResponse.success("Dashboard fetched successfully", dashboard)
        );
    }

    // ================= SET SCHEDULE =================
    @PutMapping("/{id}/schedule")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<ApiResponse<String>> setSchedule(
            @PathVariable UUID id,
            @Valid @RequestBody List<DoctorScheduleDTO> schedules) {

        doctorService.setSchedule(id, schedules);

        return ResponseEntity.ok(
                ApiResponse.success("Schedule updated successfully", null)
        );
    }

    // ================= GET DOCTORS BY DEPARTMENT =================
    @GetMapping("/departments/{deptId}")
    public ResponseEntity<ApiResponse<List<DoctorDTO>>> getDoctorsByDepartment(@PathVariable UUID deptId) {

        List<DoctorDTO> doctors = doctorService.getDoctors(deptId);

        return ResponseEntity.ok(
                ApiResponse.success("Doctors fetched successfully", doctors)
        );
    }

    // ================= ASSIGN HEAD DOCTOR =================
    @PutMapping("/departments/{deptId}/head")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DepartmentDTO>> assignHeadDoctor(
            @PathVariable UUID deptId,
            @RequestParam UUID doctorId) {

        DepartmentDTO department = doctorService.assignHeadDoctor(deptId, doctorId);

        return ResponseEntity.ok(
                ApiResponse.success("Head doctor assigned successfully", department)
        );
    }
}