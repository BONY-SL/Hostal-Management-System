package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="complain")
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complain_id;
    private String title;
    private String description;
    private Date date_reported;
    private String status;
    private Date resolved_date;

    @ManyToOne
    @JoinColumn(name = "subwarden_id")
    private SubWarden subwarden;
}
