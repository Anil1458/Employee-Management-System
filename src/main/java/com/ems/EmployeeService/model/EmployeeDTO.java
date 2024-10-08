package com.ems.EmployeeService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeDTO {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String department;
    private double salary;
}
