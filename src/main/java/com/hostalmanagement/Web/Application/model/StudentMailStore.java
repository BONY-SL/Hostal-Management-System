package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="studentmail")
public class StudentMailStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "email")
    private String email;

    @Column(length = 5, unique = true)
    private String tgnumber;

    @Column(name = "is_registerd")
    @ColumnDefault("false")
    private boolean isRegisterd;

}
