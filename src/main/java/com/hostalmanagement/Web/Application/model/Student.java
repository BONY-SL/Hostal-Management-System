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
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
  
    @Id
    @Column(name = "studentId")
    private int studentID;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "DOB")
    private String DOB;

    @Column(name = "academicYear")
    private int academicYear;
}
