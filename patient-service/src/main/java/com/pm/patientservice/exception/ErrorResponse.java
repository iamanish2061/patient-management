package com.pm.patientservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String status;
    private int httpCode;
    private String message;
    private List<String> errors;

    public ErrorResponse(String status, final int httpCode, final String message, final String error){
        this.status = status;
        this.httpCode = httpCode;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }

    public ErrorResponse(String status, int httpCode, String message, List<String> errors){
        this.status = status;
        this.httpCode = httpCode;
        this.message = message;
        this.errors = errors;
    }
}
