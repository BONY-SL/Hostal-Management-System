package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> { // JpaRepository is a JPA specific extension of Repository

   Optional<Room> findByRoomNumber(String roomNumber); // This method is used to find a room by its room number.


}
