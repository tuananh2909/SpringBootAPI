package com.ntqsolution.demo.controller;

import com.ntqsolution.demo.dto.ProjectDTO;
import com.ntqsolution.demo.dto.StatusProjectDTO;
import com.ntqsolution.demo.entity.Project;
import com.ntqsolution.demo.response.ResponseMessage;
import com.ntqsolution.demo.service.ProjectService;
import com.ntqsolution.demo.service.StatusProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private StatusProjectService statusProjectService;


    @GetMapping
    public ResponseEntity<?> getAllProject() {
        try {
            List<ProjectDTO> projects = projectService.getAllProject();
            if (projects == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (projects.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable("id") Long id) {
        try {
            ProjectDTO projectDTO = projectService.findProjectById(id);
            if (projectDTO == null || projectDTO.getId() == null) {
                return new ResponseEntity<>(new ResponseMessage("Project is not found!!!", null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new ResponseMessage("Project to look for: ", projectDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Project is not found!!!", null),
                    HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    @PostMapping
    public ResponseEntity<?> createProject(@Validated @RequestBody ProjectDTO projectDTO) {
        try {
            if (projectDTO == null) {
                return new ResponseEntity<>("Create project failed!!!", HttpStatus.BAD_REQUEST);
            }
            projectDTO.setStartDate(LocalDate.now());
            StatusProjectDTO statusProjectDTO = statusProjectService.findStatusProjectById(1L);
            projectDTO.setStatus(statusProjectDTO);
            projectDTO.setUpdateAt(LocalDate.now());
            projectService.saveOrUpdateProject(projectDTO);
            return new ResponseEntity<>(new ResponseMessage("Create project successfully", projectDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Create project failed!!!", HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Long id, @Validated @RequestBody ProjectDTO projectDTO) {
        try {
            ProjectDTO oldProject = projectService.findProjectById(id);
            if (oldProject == null) {
                return new ResponseEntity<>("Project is null!!!", HttpStatus.NOT_FOUND);
            }
            oldProject.setName(projectDTO.getName() == null ? oldProject.getName() : projectDTO.getName());
            oldProject.setStatus(projectDTO.getStatus() == null ? oldProject.getStatus() : projectDTO.getStatus());
            oldProject.setStartDate(projectDTO.getStartDate() == null ? oldProject.getStartDate() : projectDTO.getStartDate());
            oldProject.setEndDate(projectDTO.getEndDate() == null ? oldProject.getEndDate() : projectDTO.getEndDate());
            oldProject.setUpdateAt(LocalDate.now());
            projectService.saveOrUpdateProject(oldProject);
            return new ResponseEntity<>("Update project successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Update failed!!!", HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id) {
        try {
            ProjectDTO projectDTO = projectService.findProjectById(id);
            if (projectDTO == null || projectDTO.getId() == null) {
                return new ResponseEntity<>("Project is null!!!", HttpStatus.NOT_FOUND);
            }
            StatusProjectDTO status = statusProjectService.findStatusProjectById(2L);
            projectDTO.setStatus(status);
            projectDTO.setUpdateAt(LocalDate.now());
            projectService.saveOrUpdateProject(projectDTO);
            return new ResponseEntity<>("Delete project successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Delete project failed!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
