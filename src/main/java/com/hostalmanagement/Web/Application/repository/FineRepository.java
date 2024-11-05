package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.Fine;
import com.hostalmanagement.Web.Application.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine,Long> {

    @Modifying
    @Transactional
    @Query(value = "CALL hostalmanagementsystem.insert_fine(:amount, :reason, :issuedDate, :status, :tg_no)", nativeQuery = true)
    void insertFine(
            @Param("amount") double amount,
            @Param("reason") String reason,
            @Param("issuedDate") java.sql.Date issuedDate,
            @Param("status") String status,
            @Param("tg_no") String tg_no
    );


    @Procedure("update_fine_status_and_amount")
    void updateFineStatusAndAmount();



    @Query(value = "select * from fine_display", nativeQuery = true)
    List<Fine> getFineFromView();



}
