package com.hostalmanagement.Web.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAdminProfileDetails {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
}
