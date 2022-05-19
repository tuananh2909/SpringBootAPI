package com.ntqsolution.demo.service.impl;

import com.ntqsolution.demo.dto.ProjectDTO;
import com.ntqsolution.demo.entity.Project;
import com.ntqsolution.demo.repository.ProjectRepository;
import com.ntqsolution.demo.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<ProjectDTO> getAllProject() {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectDTO> projectDtos = mapper.map(projectList, new TypeToken<List<ProjectDTO>>() {
        }.getType());
        return projectDtos;
    }

    @Override
    public Project findProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        return projectOptional.orElseGet(Project::new);
    }

    @Override
    public Project saveOrUpdateProject(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }
}
