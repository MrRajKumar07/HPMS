package com.l2p.hmps.mapper;

import com.l2p.hmps.dto.CreatePrescriptionRequest;
import com.l2p.hmps.dto.PrescriptionResponse;
import com.l2p.hmps.model.Appointment;
import com.l2p.hmps.model.Prescription;
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
public class PrescriptionMapperImpl implements PrescriptionMapper {

    @Override
    public Prescription toEntity(CreatePrescriptionRequest request) {
        if ( request == null ) {
            return null;
        }

        Prescription prescription = new Prescription();

        prescription.setMedicationName( request.getMedicationName() );
        prescription.setDosage( request.getDosage() );
        prescription.setFrequency( request.getFrequency() );
        prescription.setDuration( request.getDuration() );
        prescription.setInstructions( request.getInstructions() );

        return prescription;
    }

    @Override
    public PrescriptionResponse toResponse(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }

        PrescriptionResponse prescriptionResponse = new PrescriptionResponse();

        prescriptionResponse.setPatientId( prescriptionPatientId( prescription ) );
        prescriptionResponse.setDoctorId( prescriptionDoctorId( prescription ) );
        prescriptionResponse.setAppointmentId( prescriptionAppointmentId( prescription ) );
        prescriptionResponse.setActive( prescription.isActive() );
        prescriptionResponse.setFilled( prescription.isFilled() );
        prescriptionResponse.setId( prescription.getId() );
        prescriptionResponse.setMedicationName( prescription.getMedicationName() );
        prescriptionResponse.setDosage( prescription.getDosage() );
        prescriptionResponse.setFrequency( prescription.getFrequency() );
        prescriptionResponse.setDuration( prescription.getDuration() );
        prescriptionResponse.setInstructions( prescription.getInstructions() );
        prescriptionResponse.setFilledBy( prescription.getFilledBy() );
        prescriptionResponse.setFilledAt( prescription.getFilledAt() );
        prescriptionResponse.setCreatedAt( prescription.getCreatedAt() );
        prescriptionResponse.setUpdatedAt( prescription.getUpdatedAt() );

        return prescriptionResponse;
    }

    private UUID prescriptionPatientId(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }
        User patient = prescription.getPatient();
        if ( patient == null ) {
            return null;
        }
        UUID id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID prescriptionDoctorId(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }
        User doctor = prescription.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        UUID id = doctor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID prescriptionAppointmentId(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }
        Appointment appointment = prescription.getAppointment();
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
