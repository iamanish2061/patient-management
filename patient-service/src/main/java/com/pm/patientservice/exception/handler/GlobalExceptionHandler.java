package com.pm.patientservice.exception.handler;

import com.pm.patientservice.constants.ExceptionConstants;
import com.pm.patientservice.exception.ErrorResponse;
import com.pm.patientservice.exception.custom.BadRequestException;
import com.pm.patientservice.exception.custom.ConflictException;
import com.pm.patientservice.exception.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage)
                .toList();

        ErrorResponse response = new ErrorResponse(
                ExceptionConstants.FAIL,
                HttpStatus.BAD_REQUEST.value(),
                errors.size() == 1 ? errors.get(0) : ExceptionConstants.INVALID_INPUTS,
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex){
        ErrorResponse response = new ErrorResponse(
                ExceptionConstants.FAIL,
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex){
        ErrorResponse response = new ErrorResponse(
                ExceptionConstants.FAIL,
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getMessage()
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex){
        ErrorResponse response = new ErrorResponse(
                ExceptionConstants.FAIL,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
