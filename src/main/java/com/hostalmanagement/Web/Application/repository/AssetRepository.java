package com.hostalmanagement.Web.Application.repository;


import com.hostalmanagement.Web.Application.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset,String> {
}
