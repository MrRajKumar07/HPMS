package com.l2p.hmps.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.l2p.hmps.dto.DoctorDTO;
import com.l2p.hmps.model.Doctor;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "department", ignore = true)

    Doctor toEntity(DoctorDTO dto);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "department.id", target = "departmentId")
    DoctorDTO toDTO(Doctor doctor);
}
