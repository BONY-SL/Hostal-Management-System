package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.PowerConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PowerConsumptionRepository extends JpaRepository<PowerConsumption, Integer> {

     // Using @Procedure to call the stored procedure
    @Procedure(procedureName = "GetPowerConsumptionByRoomId")
    List<Object[]> getPowerConsumptionByRoomId(@Param("room_id_param") String roomId);


}
