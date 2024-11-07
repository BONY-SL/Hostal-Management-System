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
@Table(name = "requestroom",indexes ={@Index(name = "idx_requestRoom_room_no",columnList = "room_no")})
public class RequestRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="room_no",nullable = false)
    private String room_no;

    @Column(name="request_date")
    private Date request_date;

    @Column(name = "state")
    private String state="pending";


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tg_no", referencedColumnName = "tg_no", nullable = false)
    private Student student;


}
