package com.ems.EmployeeService.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {
    @Id
    private String employeeID;
    private String employeeFirstName;
    private String employeeLastName;
    private int employeeAge;
    private String employeeDepartment;
    private double employeeSalary;
}
