package com.ntqsolution.demo.service.impl;

import com.ntqsolution.demo.dto.StatusProjectDTO;
import com.ntqsolution.demo.entity.StatusProject;
import com.ntqsolution.demo.repository.StatusProjectRepository;
import com.ntqsolution.demo.service.StatusProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusProjectServiceImpl implements StatusProjectService {
    @Autowired
    private StatusProjectRepository statusProjectRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public StatusProjectDTO findStatusProjectById(Long id) {
        Optional<StatusProject> statusProjectOptional = statusProjectRepository.findById(id);
        StatusProject statusProject = statusProjectOptional.orElseGet(StatusProject::new);
        return mapper.map(statusProject, StatusProjectDTO.class);
    }
}
