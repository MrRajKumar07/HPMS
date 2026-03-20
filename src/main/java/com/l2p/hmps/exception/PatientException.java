package com.l2p.hmps.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception class for all Patient-related errors
 */
public class PatientException extends HpmsException {

    public PatientException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}