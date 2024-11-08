package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.CreateRoomRequest;
import com.hostalmanagement.Web.Application.model.Room;
import com.hostalmanagement.Web.Application.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // This annotation is used to mark the class as a service class.
public class RoomService {


    private RoomRepository roomRepository; // This is the object of RoomRepository.

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }   // This is the constructor of the RoomService class.


    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }   // This method is used to get all the rooms from the database.


    public Room getRoomByRoomNumber(String room_id) {
       return roomRepository.findByRoomNumber(room_id).orElseThrow(() -> new RuntimeException("Room not found with id: " + room_id));
    }// This method is used to get a room by its id from the database.

}


