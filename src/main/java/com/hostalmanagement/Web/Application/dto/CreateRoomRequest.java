package com.hostalmanagement.Web.Application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomRequest {


    private Long id;
    private String roomNumber;
    private int floorNumber;
    private int roomCapacity;
    private String description;
    private Long buildingId;
}
