package com.l2p.hmps.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.l2p.hmps.dto.ApiResponse;
import com.l2p.hmps.dto.PatientDTO;
import com.l2p.hmps.service.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // ✅ PUBLIC - Register Patient
    @PostMapping
    public ResponseEntity<ApiResponse<PatientDTO>> register(
            @Valid @RequestBody PatientDTO dto) {

        PatientDTO patient = patientService.register(dto);

        return new ResponseEntity<>(
                ApiResponse.success("Patient registered successfully", patient),
                HttpStatus.CREATED
        );
    }

    // ✅ ADMIN / DOCTOR / RECEPTIONIST
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
    public ResponseEntity<ApiResponse<Page<PatientDTO>>> getAll(Pageable pageable) {

        Page<PatientDTO> patients = patientService.getAll(pageable);

        return ResponseEntity.ok(
                ApiResponse.success("Patients fetched successfully", patients)
        );
    }

    // ✅ SEARCH
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
    public ResponseEntity<ApiResponse<Page<PatientDTO>>> search(
            @RequestParam String q,
            Pageable pageable) {

        Page<PatientDTO> result = patientService.search(q, pageable);

        return ResponseEntity.ok(
                ApiResponse.success("Search results fetched", result)
        );
    }

    // ✅ PATIENT (OWN PROFILE)
    @GetMapping("/me")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ApiResponse<PatientDTO>> getMyProfile(
            @RequestParam UUID userId) {

        PatientDTO patient = patientService.getByUserId(userId);

        return ResponseEntity.ok(
                ApiResponse.success("Patient profile fetched", patient)
        );
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
    public ResponseEntity<ApiResponse<PatientDTO>> getById(@PathVariable UUID id) {

        PatientDTO patient = patientService.getByUserId(id); // ⚠️ hack

        return ResponseEntity.ok(
                ApiResponse.success("Patient fetched successfully", patient)
        );
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    public ResponseEntity<ApiResponse<PatientDTO>> update(
            @PathVariable UUID id,
            @Valid @RequestBody PatientDTO dto) {

        PatientDTO updated = patientService.update(id, dto);

        return ResponseEntity.ok(
                ApiResponse.success("Patient updated successfully", updated)
        );
    }

    // ✅ DELETE (SOFT DELETE)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable UUID id) {

        patientService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.success("Patient deleted successfully", null)
        );
    }
}