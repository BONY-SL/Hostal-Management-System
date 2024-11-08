package com.hostalmanagement.Web.Application.dto;

import com.hostalmanagement.Web.Application.model.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    private Long student_id;
    private String tg_no;
    private String dob;
    private Date enrollmentDate;
    private String department;
    private String phoneNo;
    private String email;
    private String address;
    private Integer user_id;
    private String fName;

}
