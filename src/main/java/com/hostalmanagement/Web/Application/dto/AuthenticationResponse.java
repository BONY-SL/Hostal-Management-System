package com.hostalmanagement.Web.Application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hostalmanagement.Web.Application.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Role userRole;

    private Integer userId;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
