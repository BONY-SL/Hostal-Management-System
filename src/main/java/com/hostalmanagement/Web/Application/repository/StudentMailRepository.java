package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.StudentMailStore;
import com.hostalmanagement.Web.Application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentMailRepository extends JpaRepository<StudentMailStore,Integer> {

    Optional<StudentMailStore> findByEmail(String email);
}
