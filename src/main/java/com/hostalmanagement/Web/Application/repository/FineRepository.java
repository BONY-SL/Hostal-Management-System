package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.Fine;
import com.hostalmanagement.Web.Application.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine,Long> {
/*
    @Procedure(name = "hostalmanagementsystem.insert_fine")
    void insertFine(
            @Param("p_amount") double amount,
            @Param("p_reason") String reason,
            @Param("p_issuedDate") java.sql.Date issuedDate,
            @Param("p_status") String status,
            @Param("p_studentID") Long studentID
    );


    @Procedure("update_fine_status_and_amount")
    void updateFineStatusAndAmount();


    @Query(value = "select * from fine_display", nativeQuery = true)
    List<Fine> getFineFromView();

 */

}
