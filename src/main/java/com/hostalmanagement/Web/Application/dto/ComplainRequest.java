package com.hostalmanagement.Web.Application.dto;

import lombok.Data;

@Data // Lombok annotation to generate getter and setter methods
public class ComplainRequest { // This class is used to map the request body to the object

    private String roomNumber;
    private String complainType;
    private String description;
    private String contactNumber;
    private String status;
    private Long studentId;


}
