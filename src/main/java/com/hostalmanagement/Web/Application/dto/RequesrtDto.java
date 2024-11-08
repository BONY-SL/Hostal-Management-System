package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequesrtDto {

    private int id;
    private String room_no;
    private Date request_date;
    private String state;
    private String tg_no;


}
