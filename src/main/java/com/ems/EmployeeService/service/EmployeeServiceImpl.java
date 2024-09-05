package com.ems.EmployeeService.service;

import com.ems.EmployeeService.entity.Employee;
import com.ems.EmployeeService.error.EmployeeNotFoundException;
import com.ems.EmployeeService.mapper.EmployeeMapper;
import com.ems.EmployeeService.model.EmployeeDTO;
import com.ems.EmployeeService.repo.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        Employee emp = EmployeeMapper.mapperInstance.toEntity(employeeDTO);
        Employee employee = employeeRepository.save(emp);
        return EmployeeMapper.mapperInstance.toDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<Employee> employeesList = employeeRepository.findAll();
        return employeesList.stream()
                .map(EmployeeMapper.mapperInstance::toDTO).toList();
    }

    @Override
    public EmployeeDTO getEmployeeByID(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with the id: "+id,
                        "EMPLOYEE_NOT_FOUND", HttpStatus.NOT_FOUND));
        return EmployeeMapper.mapperInstance.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) {
        Employee existingEmp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with the id: " + id,
                        "EMPLOYEE_NOT_FOUND", HttpStatus.NOT_FOUND));

        Employee updatedEmp = EmployeeMapper.mapperInstance.toEntity(employeeDTO);
        BeanUtils.copyProperties(updatedEmp, existingEmp);
        Employee savedEmp = employeeRepository.save(existingEmp);
        return EmployeeMapper.mapperInstance.toDTO(savedEmp);
    }

    @Override
    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeDTO> getPaginatedEmployee(int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Employee> employeePage = employeeRepository.findAll(pageRequest);
        return employeePage.map(EmployeeMapper.mapperInstance::toDTO);
    }
}
