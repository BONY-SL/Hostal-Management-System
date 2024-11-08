package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "room") // This tells Hibernate to name the table as User and not User
@Data // Lombok annotation to generate Getters and Setters
public class Room {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "floor_number")
    private int floorNumber;

    @Column(name = "room_capacity")
    private int roomCapacity;

    @Column(name = "room_type")
    private String description;

    @Column(name = "building_id")
    private Long buildingId;

}
