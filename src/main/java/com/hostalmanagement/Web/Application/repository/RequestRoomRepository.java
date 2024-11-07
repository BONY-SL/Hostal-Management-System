package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.RequestRoom;
import com.hostalmanagement.Web.Application.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RequestRoomRepository extends JpaRepository<RequestRoom,Integer> {

    @Query(value = "SELECT * FROM RequestDate", nativeQuery = true)
    List<RequestRoom> getRequestRoomFromView();

}
