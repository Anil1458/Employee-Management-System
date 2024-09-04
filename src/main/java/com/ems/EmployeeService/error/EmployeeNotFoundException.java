package com.ems.EmployeeService.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeNotFoundException extends RuntimeException {

    private HttpStatus statusCode;
    private String statusMessage;

    public EmployeeNotFoundException(String message, String statusMessage, HttpStatus statusCode) {
        super(message);
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
    }
}
