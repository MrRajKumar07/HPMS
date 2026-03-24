package com.l2p.hmps.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MedicalRecordException extends RuntimeException {

    private final HttpStatus status;
    private final String errorCode;

    public MedicalRecordException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }
}