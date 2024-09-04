package com.ems.EmployeeService.repo;

import com.ems.EmployeeService.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
