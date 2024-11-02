package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> { // JpaRepository is a JPA specific extension of Repository

}
