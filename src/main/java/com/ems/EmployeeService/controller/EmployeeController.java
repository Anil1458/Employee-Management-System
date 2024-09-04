package com.ems.EmployeeService.controller;

import com.ems.EmployeeService.model.EmployeeDTO;
import com.ems.EmployeeService.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping(value = "/save")
    public ResponseEntity<EmployeeDTO> saveEmployeeHandler(@RequestBody EmployeeDTO employeeDTO){

        return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<EmployeeDTO>>  getEmployeesHandler(){

        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeByIDHandler(@PathVariable String id){
        return ResponseEntity.ok(employeeService.getEmployeeByID(id));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeHandler(@PathVariable String id,
                                                             @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteEmployeeHandler(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
