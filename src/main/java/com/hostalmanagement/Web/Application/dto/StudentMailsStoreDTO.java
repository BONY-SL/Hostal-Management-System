package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentMailsStoreDTO {

    private String email;
    private String tgnumber;

}
