package com.l2p.hmps.mapper;

import com.l2p.hmps.dto.CreateMedicalRecordRequest;
import com.l2p.hmps.dto.MedicalRecordResponse;
import com.l2p.hmps.model.Appointment;
import com.l2p.hmps.model.MedicalRecord;
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
public class MedicalRecordMapperImpl implements MedicalRecordMapper {

    @Override
    public MedicalRecord toEntity(CreateMedicalRecordRequest request) {
        if ( request == null ) {
            return null;
        }

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setVisitDate( request.getVisitDate() );
        medicalRecord.setChiefComplaint( request.getChiefComplaint() );
        medicalRecord.setDiagnosis( request.getDiagnosis() );
        medicalRecord.setIcd10Code( request.getIcd10Code() );
        medicalRecord.setSymptoms( request.getSymptoms() );
        medicalRecord.setTreatmentPlan( request.getTreatmentPlan() );
        medicalRecord.setFollowUpDate( request.getFollowUpDate() );
        medicalRecord.setWeight( request.getWeight() );
        medicalRecord.setHeight( request.getHeight() );
        medicalRecord.setTemperature( request.getTemperature() );
        medicalRecord.setBloodPressure( request.getBloodPressure() );
        medicalRecord.setHeartRate( request.getHeartRate() );
        medicalRecord.setSpo2( request.getSpo2() );

        return medicalRecord;
    }

    @Override
    public MedicalRecordResponse toResponse(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }

        MedicalRecordResponse medicalRecordResponse = new MedicalRecordResponse();

        medicalRecordResponse.setPatientId( medicalRecordPatientId( medicalRecord ) );
        medicalRecordResponse.setDoctorId( medicalRecordDoctorId( medicalRecord ) );
        medicalRecordResponse.setAppointmentId( medicalRecordAppointmentId( medicalRecord ) );
        medicalRecordResponse.setId( medicalRecord.getId() );
        medicalRecordResponse.setVisitDate( medicalRecord.getVisitDate() );
        medicalRecordResponse.setChiefComplaint( medicalRecord.getChiefComplaint() );
        medicalRecordResponse.setDiagnosis( medicalRecord.getDiagnosis() );
        medicalRecordResponse.setIcd10Code( medicalRecord.getIcd10Code() );
        medicalRecordResponse.setSymptoms( medicalRecord.getSymptoms() );
        medicalRecordResponse.setTreatmentPlan( medicalRecord.getTreatmentPlan() );
        medicalRecordResponse.setFollowUpDate( medicalRecord.getFollowUpDate() );
        medicalRecordResponse.setWeight( medicalRecord.getWeight() );
        medicalRecordResponse.setHeight( medicalRecord.getHeight() );
        medicalRecordResponse.setTemperature( medicalRecord.getTemperature() );
        medicalRecordResponse.setBloodPressure( medicalRecord.getBloodPressure() );
        medicalRecordResponse.setHeartRate( medicalRecord.getHeartRate() );
        medicalRecordResponse.setSpo2( medicalRecord.getSpo2() );

        return medicalRecordResponse;
    }

    private UUID medicalRecordPatientId(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }
        User patient = medicalRecord.getPatient();
        if ( patient == null ) {
            return null;
        }
        UUID id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID medicalRecordDoctorId(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }
        User doctor = medicalRecord.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        UUID id = doctor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID medicalRecordAppointmentId(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }
        Appointment appointment = medicalRecord.getAppointment();
        if ( appointment == null ) {
            return null;
        }
        UUID id = appointment.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
