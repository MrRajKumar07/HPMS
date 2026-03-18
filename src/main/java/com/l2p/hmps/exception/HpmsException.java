package com.l2p.hmps.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Base abstract exception for all HPMS domain exceptions 
 */
@Getter
public abstract class HpmsException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorCode;

    protected HpmsException(String message, HttpStatus httpStatus, String errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}