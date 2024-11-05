package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asset_id;

    private Long room_no;
    private String description;
    private String location;
    private Date acquisition_date;
    private String asset_condition;

    @ManyToOne
    @JoinColumn(name = "tg_no", referencedColumnName = "tg_no", nullable = true)
    private Student student;
}
