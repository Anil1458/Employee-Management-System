package com.ems.EmployeeService.service;

import com.ems.EmployeeService.model.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getEmployees();

    EmployeeDTO getEmployeeByID(String id);

    EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO);

    void deleteEmployee(String id);
}
