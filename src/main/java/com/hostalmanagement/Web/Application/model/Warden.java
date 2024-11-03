package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warden")
public class Warden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wardenID;

    private String firstName;

    private String lastName;

    private String gender;

    private Date DOB;

    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "warden",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Notice> notices=new ArrayList<>();

}
