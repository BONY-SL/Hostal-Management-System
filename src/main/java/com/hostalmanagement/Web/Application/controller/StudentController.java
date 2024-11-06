package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class StudentController {
    private final StudentService studentService;

@Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")

    public ResponseEntity<?> getAllStudent(){
      return ResponseEntity.ok(studentService.getAllStudent());
    }
}
