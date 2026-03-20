package com.l2p.hmps.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppointmentException extends RuntimeException {

    private final HttpStatus status;
    private final String errorCode;

    public AppointmentException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

}