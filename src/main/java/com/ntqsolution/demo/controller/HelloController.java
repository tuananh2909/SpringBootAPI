package com.ntqsolution.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HelloController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Welcome to my project!!!");
    }

    @GetMapping("/home")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok("Welcome to home project");
    }
}
