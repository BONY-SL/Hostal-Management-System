package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "student1")
@Table(name = "student1")
@Data
public class Student {
    @Id
    @Column(name = "student_id")
    private int studentID;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "dob")
    private String DOB;

    @Column(name = "academic_year")
    private int academicYear;
}
