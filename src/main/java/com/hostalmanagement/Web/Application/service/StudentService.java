package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.StudentDto;
import com.hostalmanagement.Web.Application.model.Student;
import com.hostalmanagement.Web.Application.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

private StudentRepo studentRepo;

public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
}

public List<Student> getAllStudent(){
     return  studentRepo.findAll();
}

}
