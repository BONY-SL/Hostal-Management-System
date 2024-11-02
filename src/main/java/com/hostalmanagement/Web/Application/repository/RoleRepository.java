package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(String role);
}
