package com.l2p.hmps.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.l2p.hmps.dto.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles custom HPMS domain exceptions 
     */
    @ExceptionHandler(HpmsException.class)
    public ResponseEntity<ApiResponse<String>> handleHpmsException(HpmsException ex) {
        log.error("Domain Exception: {} - Code: {}", ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(
            ApiResponse.error(ex.getMessage(), ex.getErrorCode()), 
            ex.getHttpStatus()
        );
    }

    /**
     * Handles @Valid validation failures for DTOs 
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        // FIXED: Using builder to include errorCode for validation
        return new ResponseEntity<>(
            ApiResponse.<Map<String, String>>builder()
                .success(false)
                .message("Validation failed")
                .errorCode("VALIDATION_400")
                .data(errors)
                .build(), 
            HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Fallback for unexpected 500 errors 
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception ex) {
        log.error("Unexpected error occurred: ", ex);
        return new ResponseEntity<>(
            ApiResponse.error("An unexpected error occurred. Please contact support.", "INTERNAL_500"), 
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    
    /**
     * Handles Access Denied (Spring Security)
     */
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccessDenied(AuthorizationDeniedException ex) {
        log.warn("Security Alert: Access Denied to resource");
        return new ResponseEntity<>(
            ApiResponse.error("Access Denied: You do not have the required role.", "AUTH_403"),
            HttpStatus.FORBIDDEN
        );
    }
}