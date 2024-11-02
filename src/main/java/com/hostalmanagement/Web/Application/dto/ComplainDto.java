package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComplainDto {

    private Long complain_id;
    private String title;
    private String description;
    private Date date_reported;
    private String status;
    private Date date_resolved;
    private Long subwarden_id;
}
