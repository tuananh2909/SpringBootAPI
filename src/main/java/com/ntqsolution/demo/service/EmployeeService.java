package com.ntqsolution.demo.service;

import com.ntqsolution.demo.dto.EmployeeDTO;
import com.ntqsolution.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO findEmployeeById(Long id);

    void saveOrUpdateEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(EmployeeDTO employeeDTO);
}
