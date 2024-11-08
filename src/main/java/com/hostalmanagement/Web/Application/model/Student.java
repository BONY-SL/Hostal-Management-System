package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student", indexes = {
        @Index(name = "idx_student_tg_no", columnList = "tg_no")
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentID;

    @Column(name = "tg_no")
    private String tg_no;

    @Column(name = "DOB",nullable = true)
    private String dob;

    @Column(name = "enrollment_date",nullable = true)
    private Date enrollmentDate;

    @Column(name = "department",nullable = true)
    private String department;

    @Column(name = "phone_no",nullable = true)
    private String phoneNo;

    @Column(name = "email",nullable = true)
    private String email;

    @Column(name = "address",nullable = true)
    private String address;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User user1;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fine> fines = new ArrayList<>();

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asset> assets=new ArrayList<>();


    @OneToOne(mappedBy = "student")
    private OutgoingDetails outgoingDetails;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complain> complains = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestRoom> requestRooms = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegisterStudent> registerStudents = new ArrayList<>();


}
