package com.l2p.hmps.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.l2p.hmps.dto.PatientDTO;
import com.l2p.hmps.model.Patient;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    // DTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nhsId", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)

    @Mapping(target = "user", ignore = true)

    Patient toEntity(PatientDTO dto);
    // Entity -> DTO
    @Mapping(source = "user.id", target = "userId") // ✅ IMPORTANT
    PatientDTO toDTO(Patient patient);
}