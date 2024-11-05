package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long studentID;
    private String tg_no;
    private String firstName;
    private String lastName;
    private String dob;
    private Date enrollmentDate;
    private String department;
    private String phoneNo;
    private String email;
    private String address;
}
