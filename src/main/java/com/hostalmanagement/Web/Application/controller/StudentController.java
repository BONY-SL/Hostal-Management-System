package com.hostalmanagement.Web.Application.controller;


import com.hostalmanagement.Web.Application.dto.StudentDto;
import com.hostalmanagement.Web.Application.service.StudentService;
import lombok.RequiredArgsConstructor;

import com.hostalmanagement.Web.Application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@RestController

@RequestMapping("/hostel")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/getStudent")
    public ResponseEntity<List<StudentDto>> getStudentDetails() {
        System.out.println("Received request to retrieve student details");
        List<StudentDto> studentDtos=studentService.getAllStudents();
        return ResponseEntity.ok().body(studentDtos);

    }
}
