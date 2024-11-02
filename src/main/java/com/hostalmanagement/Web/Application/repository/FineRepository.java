package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FineRepository extends JpaRepository<Fine,Long> {
}
