package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubWardenDto {

    private Long subwarden_id;
    private String first_name;
    private String last_name;
    private String gender;
    private Date dob;
    private String email;
    private  String phone_no;
    private String address;
    private String assigned_hostel;
    private String status;
}
