package com.ntqsolution.demo.service;

import com.ntqsolution.demo.dto.StatusEmployeeDTO;
import com.ntqsolution.demo.entity.StatusEmployee;

public interface StatusEmployeeService {

    StatusEmployeeDTO findStatusEmployeeById(Long id);
}
