package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/hostalmanage/dean")
public class DeanController {



    final private StudentService studentService;



    @Autowired
    public DeanController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudent(){

        return ResponseEntity.ok(studentService.getAllStudents());
    }



}
