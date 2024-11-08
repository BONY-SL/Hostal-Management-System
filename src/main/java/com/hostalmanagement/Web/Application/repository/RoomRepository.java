package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.dto.CreateRoomRequest;
import com.hostalmanagement.Web.Application.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> { // JpaRepository is a JPA specific extension of Repository

   Optional<Room> findByRoomNumber(String roomNumber);// This method is used to find a room by its room number.


   @Procedure(name = "createRoom")
   void createRoom(
           @Param("p_roomNumber") String roomNumber,
           @Param("p_floorNumber") int floorNumber,
           @Param("p_roomCapacity") int roomCapacity,
           @Param("p_description") String description,
           @Param("p_buildingId") Long buildingId
   );

   // Custom query to fetch all rooms from the 'getAllRooms' view
   @Query("SELECT new com.hostalmanagement.Web.Application.dto.CreateRoomRequest(r.id, r.roomNumber, r.floorNumber, r.roomCapacity, r.description, r.buildingId) FROM Room r")
   List<CreateRoomRequest> findAllRooms();

}
