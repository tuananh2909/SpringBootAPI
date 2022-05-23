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
    public ProjectDTO findProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        Project project = projectOptional.orElseGet(Project::new);
        return mapper.map(project, new ProjectDTO().getClass());
    }

    @Override
    public void saveOrUpdateProject(ProjectDTO projectDTO) {
        projectRepository.saveAndFlush(mapper.map(projectDTO, new Project().getClass()));
    }

    @Override
    public void deleteProject(ProjectDTO projectDTO) {
        projectRepository.delete(mapper.map(projectDTO, new Project().getClass()));
    }


}
