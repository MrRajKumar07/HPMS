package com.l2p.hmps.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.l2p.hmps.dto.DoctorScheduleDTO;
import com.l2p.hmps.model.DoctorSchedule;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctor", ignore = true)

    DoctorSchedule toEntity(DoctorScheduleDTO dto);

    @Mapping(source = "doctor.id", target = "doctorId")
    DoctorScheduleDTO toDTO(DoctorSchedule schedule);
}