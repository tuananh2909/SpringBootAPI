package com.ntqsolution.demo.service;

import com.ntqsolution.demo.dto.ProjectDTO;
import com.ntqsolution.demo.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<ProjectDTO> getAllProject();

    ProjectDTO findProjectById(Long id);

    void saveOrUpdateProject(ProjectDTO projectDTO);

    void deleteProject(ProjectDTO projectDTO);
}
