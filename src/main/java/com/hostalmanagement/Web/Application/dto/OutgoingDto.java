package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutgoingDto {
    private int outgoingId;
    private String studentName;
    private String location;
    private LocalDate date;
    private LocalTime arrivalTime;
    private LocalTime leaveTime;
}
