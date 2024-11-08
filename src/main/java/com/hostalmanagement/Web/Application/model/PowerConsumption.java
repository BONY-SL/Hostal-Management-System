package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "power_consumption")
@Data
public class PowerConsumption {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @Column(name = "consumption_value")
    private float powerConsumption;

    @Column(name = "unit")
    private String unit;

    @Column(name = "recorded_at")
    private String date;

    @Column(name = "location")
    private String location;

    @Column(name = "remarks")
    private String remarks;

//    // Change the field name from roomId to room
//    @ManyToOne
//    @JoinColumn(name = "room_id", referencedColumnName = "room_number", nullable = true)
//    private Room room;  // Change "roomId" to "room" to match the mappedBy value in Room




}
