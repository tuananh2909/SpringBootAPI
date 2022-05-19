package com.ntqsolution.demo.controller;

import com.ntqsolution.demo.dto.ProjectDTO;
import com.ntqsolution.demo.entity.Project;
import com.ntqsolution.demo.response.ResponseMessage;
import com.ntqsolution.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;


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
            Project project = projectService.findProjectById(id);
            if (project == null || project.getId() == null) {
                return new ResponseEntity<>(new ResponseMessage("Project is not found!!!", null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new ResponseMessage("Project to look for: ", project), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Project is not found!!!", null),
                    HttpStatus.GATEWAY_TIMEOUT);
        }
    }

}
