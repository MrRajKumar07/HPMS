package com.l2p.hmps.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.l2p.hmps.dto.PatientDTO;
import com.l2p.hmps.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // POST /api/v1/patients
    @PostMapping
    public ResponseEntity<PatientDTO> register(@Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO patient = patientService.register(patientDTO);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    // GET /api/v1/patients?page=&size=
    @GetMapping
    public ResponseEntity<Page<PatientDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(patientService.getAll(pageable));
    }

    // GET /api/v1/patients/search?q=&page=
    @GetMapping("/search")
    public ResponseEntity<Page<PatientDTO>> search(@RequestParam String q, Pageable pageable) {
        return ResponseEntity.ok(patientService.search(q, pageable));
    }

    // GET /api/v1/patients/me
    @GetMapping("/me")
    public ResponseEntity<PatientDTO> getMyProfile(@RequestParam UUID userId) {
        return ResponseEntity.ok(patientService.getByUserId(userId));
    }

    // GET /api/v1/patients/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(patientService.getByUserId(id));
    }

    // PUT /api/v1/patients/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable UUID id,
                                             @Valid @RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.update(id, patientDTO));
    }

    // DELETE /api/v1/patients/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}