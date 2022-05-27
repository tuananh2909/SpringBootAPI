package com.ntqsolution.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
public class HelloController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Welcome to my project!!!");
    }

    @GetMapping("/home")
    public ResponseEntity<?> home(HttpServletRequest request) {
        System.out.println(request.isUserInRole("ROLE_ADMIN"));
        return ResponseEntity.ok("Welcome to home project");
    }
}
