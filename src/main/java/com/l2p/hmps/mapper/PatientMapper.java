package com.l2p.hmps.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.l2p.hmps.dto.PatientDTO;
import com.l2p.hmps.model.Patient;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    // DTO -> Entity
    @Mapping(target = "id", ignore = true) // usually generated
    @Mapping(target = "nhsId", ignore = true) // auto-generated
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Patient toEntity(PatientDTO dto);

    // Entity -> DTO
    PatientDTO toDTO(Patient patient);
}