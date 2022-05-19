package com.ntqsolution.demo.service.impl;

import com.ntqsolution.demo.dto.EmployeeDTO;
import com.ntqsolution.demo.entity.Employee;
import com.ntqsolution.demo.repository.EmployeeRepository;
import com.ntqsolution.demo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();

        List<EmployeeDTO> employeeDTOS = mapper.map(employeeList, new TypeToken<List<EmployeeDTO>>() {
        }.getType());

        return employeeDTOS;
    }

    @Override
    public EmployeeDTO findEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Employee employee = employeeOptional.orElseGet(Employee::new);
        return mapper.map(employee, new EmployeeDTO().getClass());
    }
}
