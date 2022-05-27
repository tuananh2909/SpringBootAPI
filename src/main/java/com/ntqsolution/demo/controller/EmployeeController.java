package com.ntqsolution.demo.controller;

import com.ntqsolution.demo.dto.EmployeeDTO;
import com.ntqsolution.demo.dto.StatusEmployeeDTO;
import com.ntqsolution.demo.response.ResponseMessage;
import com.ntqsolution.demo.service.EmployeeService;
import com.ntqsolution.demo.service.StatusEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StatusEmployeeService statusEmployeeService;


    @GetMapping("")
    public ResponseEntity<?> getAllEmployee() {
        try {
            List<EmployeeDTO> employees = employeeService.getAllEmployee();
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else if (employees == null) {
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
            EmployeeDTO employee = employeeService.findEmployeeById(id);
            if (employee == null || employee.getId() == null) {
                return new ResponseEntity<>(new ResponseMessage("Employee is not found!!!", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new ResponseMessage("Employee to look for: ", employee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Employee is not found!!!", null), HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@Validated @RequestBody EmployeeDTO employeeDTO) {
        try {
            if (employeeDTO == null) {
                return new ResponseEntity<>(new ResponseMessage("Employee is null!!!", null), HttpStatus.NOT_FOUND);
            }
            employeeDTO.setStartDate(LocalDate.now());
            employeeDTO.setIsOut(false);
            StatusEmployeeDTO status = statusEmployeeService.findStatusEmployeeById(1L);
            employeeDTO.setStatus(status);
            employeeDTO.setUpdateAt(LocalDate.now());
            employeeService.saveOrUpdateEmployee(employeeDTO);
            return new ResponseEntity<>(new ResponseMessage("Create successfully", employeeDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Create failed!!!", null), HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        try {
            EmployeeDTO employeeDTO = employeeService.findEmployeeById(id);
            if (employeeDTO == null || employeeDTO.getId() == null) {
                return new ResponseEntity<>(new ResponseMessage("Employee is null!!!", null), HttpStatus.NOT_FOUND);
            }
            employeeDTO.setIsOut(true);
            employeeService.saveOrUpdateEmployee(employeeDTO);
            //employeeService.deleteEmployee(employeeDTO);
            return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Delete failed!!!", null), HttpStatus.GATEWAY_TIMEOUT);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editEmployee(@PathVariable("id") Long id, @Validated @RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeDTO oldEmployee = employeeService.findEmployeeById(id);
            if (employeeDTO == null) {
                return new ResponseEntity<>(new ResponseMessage("Update failed!!!", null), HttpStatus.BAD_REQUEST);
            }
            oldEmployee.setName(employeeDTO.getName() == null ? oldEmployee.getName() : employeeDTO.getName());
            oldEmployee.setStatus(employeeDTO.getStatus() == null ? oldEmployee.getStatus() : employeeDTO.getStatus());
            oldEmployee.setStartDate(employeeDTO.getStartDate() == null ? oldEmployee.getStartDate() : employeeDTO.getStartDate());
            oldEmployee.setIsOut(employeeDTO.getIsOut() == null ? oldEmployee.getIsOut() : employeeDTO.getIsOut());
            oldEmployee.setRole(employeeDTO.getRole() == null ? oldEmployee.getRole() : employeeDTO.getRole());
            oldEmployee.setUpdateAt(LocalDate.now());
            employeeService.saveOrUpdateEmployee(oldEmployee);
            return new ResponseEntity<>("Update successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Update failed!!!", null), HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEmployeesByName(@RequestParam("query") String query){
        return ResponseEntity.ok(employeeService.searchEmployees(query));
    }
}