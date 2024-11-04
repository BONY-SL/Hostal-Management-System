package com.hostalmanagement.Web.Application.repository;


import com.hostalmanagement.Web.Application.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {
    @Procedure(name = "insert_asset")
    void insertAsset(
            @Param("p_room_no") Long room_no,
            @Param("p_description") String description,
            @Param("p_location") String location,
            @Param("p_acquisition_date") java.sql.Date acquisition_date,
            @Param("p_assetCondition") String condition,
            @Param("p_studentID") Integer studentID
    );
}
