package com.l2p.hmps.controller;

import java.util.UUID;

import com.l2p.hmps.model.User;
import com.l2p.hmps.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<PatientDTO>> register(@Valid @RequestBody PatientDTO dto) {
        PatientDTO patient = patientService.register(dto);
        return new ResponseEntity<>(
                ApiResponse.success("Patient registered successfully", patient),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
    public ResponseEntity<ApiResponse<Page<PatientDTO>>> getAll(Pageable pageable) {
        Page<PatientDTO> patients = patientService.getAll(pageable);
        return ResponseEntity.ok(
                ApiResponse.success("Patients fetched successfully", patients)
        );
    }

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

    @GetMapping("/me")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ApiResponse<PatientDTO>> getMyProfile(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        PatientDTO patient = patientService.getByUserId(user.getId());
        return ResponseEntity.ok(ApiResponse.success("Patient profile fetched", patient));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
    public ResponseEntity<ApiResponse<PatientDTO>> getById(@PathVariable UUID id) {
        PatientDTO patient = patientService.getByUserId(id);
        return ResponseEntity.ok(ApiResponse.success("Patient fetched successfully", patient));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    public ResponseEntity<ApiResponse<PatientDTO>> update(@PathVariable UUID id, @Valid @RequestBody PatientDTO dto) {
        PatientDTO updated = patientService.update(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Patient updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable UUID id) {
        patientService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Patient deleted successfully", null));
    }
}