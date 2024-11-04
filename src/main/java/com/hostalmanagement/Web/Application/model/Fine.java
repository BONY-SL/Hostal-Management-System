package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "fine")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fineId;

    private double amount;
    private String reason;
    private Date issuedDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "studentID", nullable = false)
    private Student student;
}