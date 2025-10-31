package com.pm.patientservice.exception;

public class PatientNotfoundError extends RuntimeException {
    public PatientNotfoundError(String message) {
        super(message);
    }
}
