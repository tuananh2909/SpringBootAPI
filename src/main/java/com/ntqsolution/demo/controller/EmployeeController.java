package com.ntqsolution.demo.controller;

import com.ntqsolution.demo.dto.EmployeeDTO;
import com.ntqsolution.demo.response.ResponseMessage;
import com.ntqsolution.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployee() {
        try {
            List<EmployeeDTO> employees = employeeService.getAllEmployee();
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else if (Objects.isNull(employees)) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) {
        try {
            EmployeeDTO employees = employeeService.findEmployeeById(id);
            if (employees == null || employees.getId() == null) {
                return new ResponseEntity<>(new ResponseMessage("Employee is not found!!!", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new ResponseMessage("Employee to look for: ", employees), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Employee is not found!!!", null), HttpStatus.GATEWAY_TIMEOUT);
        }
    }


}
