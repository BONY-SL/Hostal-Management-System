package com.hostalmanagement.Web.Application.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "complain")
@Data
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complain_id")
    private Long complainId;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "complain_type")
    private String complainType;

    @Column(name = "description")
    private String Description;

    @Column(name = "contact_number")
    private String ContactNumber;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = true)
    private Student student;

}
