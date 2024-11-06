package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="subwarden")
public class SubWarden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
