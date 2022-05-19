package com.ntqsolution.demo.service;

import com.ntqsolution.demo.dto.ProjectDTO;
import com.ntqsolution.demo.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<ProjectDTO> getAllProject();

    Project findProjectById(Long id);

    Project saveOrUpdateProject(Project project);

    void deleteProject(Project project);
}
