package com.ntqsolution.demo.service;

import com.ntqsolution.demo.dto.StatusProjectDTO;
import com.ntqsolution.demo.entity.StatusProject;

public interface StatusProjectService {

    StatusProjectDTO findStatusProjectById(Long id);
}
