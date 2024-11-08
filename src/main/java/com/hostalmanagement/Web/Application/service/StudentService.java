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

                return new StudentDto(
                    student.getStudentID(),
                    student.getTg_no(),
                    student.getDob(),
                    student.getEnrollmentDate(),
                    student.getDepartment(),
                    student.getPhoneNo(),
                    student.getEmail(),
                    student.getAddress(),
                        student.getUser1().getId(),
                        student.getUser1().getFirstname()+" "+student.getUser1().getLastname()
                );
   }

    public long getStudentCount() {

        return studentRepo.count();

    }

}
