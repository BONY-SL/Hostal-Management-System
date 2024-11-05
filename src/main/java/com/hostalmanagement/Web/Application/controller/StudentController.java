package com.hostalmanagement.Web.Application.controller;
import com.hostalmanagement.Web.Application.dto.StudentDto;
import com.hostalmanagement.Web.Application.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/hostalmanage/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    /*
    @GetMapping("/getStudent")
    public ResponseEntity<List<StudentDto>> getStudentDetails() {
        System.out.println("Received request to retrieve student details");
        List<StudentDto> studentDtos=studentService.getAllStudents();
        return ResponseEntity.ok().body(studentDtos);

    }

     */

    //total count of the student
    @GetMapping("/getTotalStudentCount")
    public ResponseEntity<Long> getTotalStudentCount() {
        long totalStudentCount = studentService.getStudentCount();
        return ResponseEntity.ok(totalStudentCount);
    }
}
