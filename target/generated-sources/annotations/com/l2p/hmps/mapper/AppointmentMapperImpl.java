package com.l2p.hmps.mapper;

import com.l2p.hmps.dto.BookAppointmentRequest;
import com.l2p.hmps.model.Appointment;
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
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public Appointment toEntity(BookAppointmentRequest request) {
        if ( request == null ) {
            return null;
        }

        Appointment appointment = new Appointment();

        appointment.setAppointmentDate( request.getAppointmentDate() );
        appointment.setAppointmentTime( request.getAppointmentTime() );
        appointment.setSlot( request.getSlot() );
        appointment.setType( request.getType() );
        appointment.setReason( request.getReason() );

        return appointment;
    }

    @Override
    public BookAppointmentRequest toDTO(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        BookAppointmentRequest bookAppointmentRequest = new BookAppointmentRequest();

        bookAppointmentRequest.setPatientId( appointmentPatientId( appointment ) );
        bookAppointmentRequest.setAppointmentDate( appointment.getAppointmentDate() );
        bookAppointmentRequest.setAppointmentTime( appointment.getAppointmentTime() );
        bookAppointmentRequest.setSlot( appointment.getSlot() );
        bookAppointmentRequest.setType( appointment.getType() );
        bookAppointmentRequest.setReason( appointment.getReason() );

        return bookAppointmentRequest;
    }

    private UUID appointmentPatientId(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }
        User patient = appointment.getPatient();
        if ( patient == null ) {
            return null;
        }
        UUID id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
