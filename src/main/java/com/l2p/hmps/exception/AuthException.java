package com.l2p.hmps.exception;

import org.springframework.http.HttpStatus;

public class AuthException extends HpmsException {
	public AuthException(String message, HttpStatus httpStatus, String errorCode) {
        super(message, httpStatus, errorCode);
    }
}