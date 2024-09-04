package com.ems.EmployeeService.mapper;

import com.ems.EmployeeService.entity.Employee;
import com.ems.EmployeeService.model.EmployeeDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper mapperInstance = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "employeeID", source = "id")
    @Mapping(target = "employeeFirstName", source = "firstName")
    @Mapping(target = "employeeLastName", source = "lastName")
    @Mapping(target = "employeeAge", source = "age")
    @Mapping(target = "employeeDepartment", source = "department")
    @Mapping(target = "employeeSalary", source = "salary")
    Employee toEntity(EmployeeDTO employeeDTO);


    @InheritInverseConfiguration
    EmployeeDTO toDTO(Employee employee);
}
