package com.ems.EmployeeService.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler extends Exception{

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<EmployeeErrorResponse> employeeNotFoundExceptionHandler(EmployeeNotFoundException e){

        EmployeeErrorResponse response = EmployeeErrorResponse.builder()
                .message(e.getMessage())
                .statusCode(e.getStatusCode())
                .statusMessage(e.getStatusMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
