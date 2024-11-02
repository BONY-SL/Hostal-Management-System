package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainRepository extends JpaRepository<Complain,Long> {
}
