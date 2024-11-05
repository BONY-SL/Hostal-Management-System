package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student", indexes = {
        @Index(name = "idx_tg_no", columnList = "tg_no")
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId")
    private Long studentID;

    @Column(name = "tg_no")
    private String tg_no;

    @Column(name = "DOB")
    private String dob;

    @Column(name = "enrollment_date")
    private Date enrollmentDate;

    @Column(name = "department")
    private String department;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user1;

    @OneToMany(mappedBy = "student")
    private List<Asset> assets;

    @OneToMany(mappedBy = "student")
    private List<Fine> fines;


}
