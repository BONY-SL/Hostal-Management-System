package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "outgoing_id")
    private int outgoingId;

    @Column(name = "Student_name")
    private String studentName;

    @Column(name = "location")
    private String location;

    @Column(name = "date")
    private Date date;

    @Column(name = "arrival_time")
    private Time arrivalTime;

    @Column(name="leave_time")
    private Time leaveTime;
}
