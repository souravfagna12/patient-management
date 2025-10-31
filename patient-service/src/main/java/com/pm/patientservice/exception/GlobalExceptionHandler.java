package com.pm.patientservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException e){
        log.warn("email already exist");
        Map<String,String> errors= new HashMap<>();
        errors.put("message",e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(PatientNotfoundError.class)
    public ResponseEntity<Map<String, String>> handlePatientNotfoundError(PatientNotfoundError ex){
        log.warn("patient not found");
        Map<String,String> error=new HashMap<>();
        error.put("message",ex.getMessage());
        return ResponseEntity.ok().body(error);
    }
}
