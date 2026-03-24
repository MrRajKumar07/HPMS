package com.l2p.hmps.mapper;

import com.l2p.hmps.dto.CreatePrescriptionRequest;
import com.l2p.hmps.dto.PrescriptionResponse;
import com.l2p.hmps.model.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrescriptionMapper {

    // DTO → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "filled", ignore = true)
    @Mapping(target = "filledBy", ignore = true)
    @Mapping(target = "filledAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Prescription toEntity(CreatePrescriptionRequest request);

    // Entity → DTO
    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "appointment.id", target = "appointmentId")
    @Mapping(source = "active", target = "active")   // map directly
    @Mapping(source = "filled", target = "filled")   // map directly
    PrescriptionResponse toResponse(Prescription prescription);
}