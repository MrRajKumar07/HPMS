package com.l2p.hmps.exception;

import org.springframework.http.HttpStatus;

public class DoctorException extends HpmsException {

    public DoctorException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}
