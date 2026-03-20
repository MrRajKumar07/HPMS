package com.l2p.hmps.exception;

import org.springframework.http.HttpStatus;

public class PatientException extends HpmsException {

    public PatientException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}