package com.l2p.hmps.mapper;

import com.l2p.hmps.dto.PatientDTO;
import com.l2p.hmps.model.Patient;
import com.l2p.hmps.model.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-21T03:03:51+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public Patient toEntity(PatientDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setFirstName( dto.getFirstName() );
        patient.setLastName( dto.getLastName() );
        patient.setDateOfBirth( dto.getDateOfBirth() );
        patient.setGender( dto.getGender() );
        patient.setBloodGroup( dto.getBloodGroup() );
        patient.setPhone( dto.getPhone() );
        patient.setAddress( dto.getAddress() );
        patient.setEmergencyContact( dto.getEmergencyContact() );
        patient.setInsuranceInfo( dto.getInsuranceInfo() );

        return patient;
    }

    @Override
    public PatientDTO toDTO(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setUserId( patientUserId( patient ) );
        patientDTO.setId( patient.getId() );
        patientDTO.setNhsId( patient.getNhsId() );
        patientDTO.setFirstName( patient.getFirstName() );
        patientDTO.setLastName( patient.getLastName() );
        patientDTO.setDateOfBirth( patient.getDateOfBirth() );
        patientDTO.setGender( patient.getGender() );
        patientDTO.setBloodGroup( patient.getBloodGroup() );
        patientDTO.setPhone( patient.getPhone() );
        patientDTO.setAddress( patient.getAddress() );
        patientDTO.setEmergencyContact( patient.getEmergencyContact() );
        patientDTO.setInsuranceInfo( patient.getInsuranceInfo() );

        return patientDTO;
    }

    private UUID patientUserId(Patient patient) {
        if ( patient == null ) {
            return null;
        }
        User user = patient.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
