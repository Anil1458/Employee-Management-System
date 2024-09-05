package com.ems.EmployeeService.service;

import com.ems.EmployeeService.model.EmployeeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getEmployees();

    EmployeeDTO getEmployeeByID(String id);

    EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO);

    void deleteEmployee(String id);

    Page<EmployeeDTO> getPaginatedEmployee(int page, int size);
}
