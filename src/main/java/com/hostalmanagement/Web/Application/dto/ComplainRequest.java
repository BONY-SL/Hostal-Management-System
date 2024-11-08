package com.hostalmanagement.Web.Application.dto;

import lombok.Data;

@Data // Lombok annotation to generate getter and setter methods
public class ComplainRequest { // This class is used to map the request body to the object

    private String roomNumber;
    private String complaintType;
    private String description;
    private String contact;
    private String status = "PENDING";
    private Long studentId = 1L;


}
