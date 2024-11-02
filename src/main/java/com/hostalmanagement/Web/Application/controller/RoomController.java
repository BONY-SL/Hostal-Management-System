package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // This annotation is used to mark the class as a controller.
@RequestMapping("/api")
public class RoomController {

    private RoomService roomService; // Assume you have a service to get room data

    // This is the constructor of the RoomController class.
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms") // This annotation is used to map the HTTP GET requests onto specific handler methods.
    public ResponseEntity<?> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms()); // Returns a list of Room objects as JSON
    }
}
