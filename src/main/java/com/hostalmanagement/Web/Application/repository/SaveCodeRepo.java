package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.SaveActivatedCoeds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaveCodeRepo extends JpaRepository<SaveActivatedCoeds,Integer> {

    Optional<SaveActivatedCoeds> findByActivationCode(String code);
}
