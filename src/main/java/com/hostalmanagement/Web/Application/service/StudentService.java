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

    @Autowired
    private StudentRepo studentRepo;

    public StudentService (StudentRepo studentRepo) {
        this.studentRepo = studentRepo;

    }


    public List<StudentDto> getAllStudents() {
            List<Student> studentList = studentRepo.getStudentFromView();
            return studentList.stream().map(this::convertStudentToDTO).collect(Collectors.toList());
  }

    private StudentDto convertStudentToDTO(Student student) {

            return StudentDto.builder()
              .studentID(student.getStudentID())          // Mapping studentID to student_id
                    .tg_no(student.getTg_no())
                .dob(student.getDob())
                    .enrollmentDate(student.getEnrollmentDate())
                    .department(student.getDepartment())
                    .phoneNo(student.getPhoneNo())
                    .email(student.getEmail())
                    .address(student.getAddress())
            .build();
   }




    public long getStudentCount() {
        return studentRepo.count();
    }





}
