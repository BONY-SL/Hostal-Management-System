package com.hostalmanagement.Web.Application.dto;

import com.hostalmanagement.Web.Application.util.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}
