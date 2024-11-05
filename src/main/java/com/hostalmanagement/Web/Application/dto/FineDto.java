package com.hostalmanagement.Web.Application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class FineDto {
    private Integer fine_id;
    private double amount;
    private String reason;
    private Date issued_date;
    private String fine_status;
    private String tg_no;
}
