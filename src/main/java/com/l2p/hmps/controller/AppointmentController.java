package com.l2p.hmps.controller;

import com.l2p.hmps.dto.ApiResponse;
import com.l2p.hmps.dto.BookAppointmentRequest;
import com.l2p.hmps.model.Appointment;
import com.l2p.hmps.model.AppointmentStatus;
import com.l2p.hmps.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Appointment>> bookAppointment(@Valid @RequestBody BookAppointmentRequest request) {
        Appointment appointment = appointmentService.bookAppointment(request);
        return ResponseEntity.ok(ApiResponse.success("Appointment booked successfully", appointment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Appointment>> getAppointmentById(@PathVariable UUID id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(ApiResponse.success("Appointment fetched successfully", appointment));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ApiResponse<List<Appointment>>> getAppointmentsByPatient(@PathVariable UUID patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patientId);
        return ResponseEntity.ok(ApiResponse.success("Appointments for patient fetched successfully", appointments));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ApiResponse<List<Appointment>>> getAppointmentsByDoctor(@PathVariable UUID doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        return ResponseEntity.ok(ApiResponse.success("Appointments for doctor fetched successfully", appointments));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Appointment>> updateStatus(@PathVariable UUID id,
                                                                 @RequestParam AppointmentStatus status) {
        Appointment appointment = appointmentService.updateAppointmentStatus(id, status);
        return ResponseEntity.ok(ApiResponse.success("Appointment status updated successfully", appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Appointment>> cancelAppointment(@PathVariable UUID id) {
        Appointment appointment = appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(ApiResponse.success("Appointment canceled successfully", appointment));
    }
}