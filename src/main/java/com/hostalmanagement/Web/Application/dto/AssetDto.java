package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssetDto {
    private Long asset_id;
    private Long room_no;
    private String description;
    private String location;
    private Date acquisition_date;
    private String asset_condition;
    private String tg_no;
}
