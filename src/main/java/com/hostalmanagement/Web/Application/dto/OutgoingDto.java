package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutgoingDto {
    private int outgoingId;
    private Date date_out;
    private String location;
    private Date return_date;
    private Time arrivalTime;
    private Time leaveTime;
    private String tg_no;
    private String name;

}
