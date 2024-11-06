package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "outgoingDetails")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutgoingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outgoing_id")
    private int outgoingId;

    @Column(name = "location")
    private String location;

    @Column(name = "date_out")
    private Date dateout;

    @Column(name = "return_date")
    private Date returndate;

    @Column(name = "arrival_time")
    private Time arrivalTime;

    @Column(name="leave_time")
    private Time leaveTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tg_no", referencedColumnName = "tg_no", nullable = true)
    private Student student;
}
