package com.hostalmanagement.Web.Application.repository;


import com.hostalmanagement.Web.Application.model.Asset;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {

    @Modifying
    @Transactional
    @Query(value = "CALL insert_asset(:room_no, :description, :location, :acquisition_date, :assetCondition, :tg_no)", nativeQuery = true)
    void insertAsset(
            @Param("room_no") Long roomNo,
            @Param("description") String description,
            @Param("location") String location,
            @Param("acquisition_date") java.sql.Date acquisitionDate,
            @Param("assetCondition") String condition,
            @Param("tg_no") String tg_no
    );

    @Query(value = "select * from Asset_display", nativeQuery = true)
    List<Asset> getAssetFromView();


}
