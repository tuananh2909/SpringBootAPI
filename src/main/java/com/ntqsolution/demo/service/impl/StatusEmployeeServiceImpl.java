package com.ntqsolution.demo.service.impl;

import com.ntqsolution.demo.dto.StatusEmployeeDTO;
import com.ntqsolution.demo.entity.StatusEmployee;
import com.ntqsolution.demo.repository.StatusEmployeeRepository;
import com.ntqsolution.demo.service.StatusEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusEmployeeServiceImpl implements StatusEmployeeService {
    @Autowired
    private StatusEmployeeRepository statusEmployeeRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public StatusEmployeeDTO findStatusEmployeeById(Long id) {
        Optional<StatusEmployee> statusEmployeeOptional = statusEmployeeRepository.findById(id);
        StatusEmployee statusEmployee = statusEmployeeOptional.orElseGet(StatusEmployee::new);
        return mapper.map(statusEmployee, StatusEmployeeDTO.class);
    }
}
