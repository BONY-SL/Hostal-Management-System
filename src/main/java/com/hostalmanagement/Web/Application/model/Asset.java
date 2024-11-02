package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Asset {
    @Id
    private String asset_id;
    private String asset_name;
    private String description;
    private String category;
    private String location;
}
