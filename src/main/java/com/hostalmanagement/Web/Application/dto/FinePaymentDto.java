package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinePaymentDto {
    private String tg_no;
    private LocalDate issueDate;
    private LocalDateTime updateTime;
    private String message;
}
