package com.ems.EmployeeService.service;

import com.ems.EmployeeService.entity.Employee;
import com.ems.EmployeeService.error.EmployeeNotFoundException;
import com.ems.EmployeeService.mapper.EmployeeMapper;
import com.ems.EmployeeService.model.EmployeeDTO;
import com.ems.EmployeeService.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        Employee emp = EmployeeMapper.mapperInstance.toEntity(employeeDTO);
        Employee employee = employeeRepository.save(emp);
        log.info("the details of employee saved in data base is: {}", employee);
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
        log.error("error occurred as employee is not found with id:{}", id);
        return EmployeeMapper.mapperInstance.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) {
        Employee existingEmp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with the id: " + id,
                        "EMPLOYEE_NOT_FOUND", HttpStatus.NOT_FOUND));
        log.error("error occurred while updating employee is not found with id:{}", id);
        Employee updatedEmp = EmployeeMapper.mapperInstance.toEntity(employeeDTO);
        BeanUtils.copyProperties(updatedEmp, existingEmp);
        Employee savedEmp = employeeRepository.save(existingEmp);
        log.info("updated employee: {}", updatedEmp);
        return EmployeeMapper.mapperInstance.toDTO(savedEmp);
    }

    @Override
    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
        log.info("employee is successfully deleted with id:{}", id);
    }

    @Override
    public Page<EmployeeDTO> getPaginatedEmployee(int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Employee> employeePage = employeeRepository.findAll(pageRequest);
        log.info("employee page is successfully generated:{}", employeePage);
        return employeePage.map(EmployeeMapper.mapperInstance::toDTO);
    }
}
