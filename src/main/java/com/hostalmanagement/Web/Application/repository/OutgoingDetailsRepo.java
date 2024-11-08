package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.OutgoingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutgoingDetailsRepo extends JpaRepository<OutgoingDetails, Integer> {
    @Query (value = "SELECT * FROM OutgoingView ",nativeQuery = true)
    List<OutgoingDetails>getOutgoingFromView();
}
