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
    private Integer fineId;

    private double amount;
    private String reason;
    private Date issuedDate;
    private String fine_status;

    @ManyToOne
    @JoinColumn(name = "tg_no", referencedColumnName = "tg_no", nullable = true)
    private Student student;
}
