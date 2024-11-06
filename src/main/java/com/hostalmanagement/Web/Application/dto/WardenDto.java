package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WardenDto {
    private Long wardenID;

    private String firstName;
    private String lastName;
    private String gender;
    private Date DOB;
    private String email;
    private String phoneNumber;
}