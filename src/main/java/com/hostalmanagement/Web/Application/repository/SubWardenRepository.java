package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.SubWarden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubWardenRepository extends JpaRepository<SubWarden,Long> {

}
