package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "fine")
@Table(name = "fine")
public class Fine {

    @Id
    @Column(name = "fineID")
    private String fineId;

    @Column(name = "fineName")
    private String fineName;

    @Column(name = "fineType")
    private String fineType;

}
