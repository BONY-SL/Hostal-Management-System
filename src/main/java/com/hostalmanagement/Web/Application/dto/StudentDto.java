package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long studentId;
    private String address;
    private String department;
    private String dob;
    private String email;
    private String enrollmentDate;
    private String phoneNo;
    private Long userId;
}
