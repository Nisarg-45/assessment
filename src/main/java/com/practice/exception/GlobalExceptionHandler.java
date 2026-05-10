package com.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.practice.model.dto.response.GenericResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GenericResponse<Object>> handleResourceNotFound(
            ResourceNotFoundException ex) {  

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GenericResponse<>(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<Object>> handleGeneralException(
            Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GenericResponse<>(ex.getMessage()));
    }
    
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<
            GenericResponse<Object>> 
    handleBadRequest(
            BadRequestException ex) {

        return ResponseEntity
                .badRequest()
                .body(
                        new GenericResponse<>(ex.getMessage()
                        )
                );
    }
}    