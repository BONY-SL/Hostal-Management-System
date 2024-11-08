package com.hostalmanagement.Web.Application.controller;
import com.hostalmanagement.Web.Application.dto.ComplainRequest;
import com.hostalmanagement.Web.Application.dto.StudentDto;
import com.hostalmanagement.Web.Application.model.PowerConsumption;
import com.hostalmanagement.Web.Application.service.ComplainService;
import com.hostalmanagement.Web.Application.service.PowerConsumptionService;
import com.hostalmanagement.Web.Application.service.RoomService;
import com.hostalmanagement.Web.Application.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping("/hostalmanage/student")
@PreAuthorize("hasAuthority('STUDENT')")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService; // Assume you have a service to get student data
    private final RoomService roomService; // Assume you have a service to get room data
    private final ComplainService complainService; // Assume you have a service to add a complaint
    private final PowerConsumptionService powerConsumptionService;  // Assume you have a service to get power consumption data


    @GetMapping("/getStudent")
    public ResponseEntity<List<StudentDto>> getStudentDetails() {
        System.out.println("Received request to retrieve student details");
        List<StudentDto> studentDtos=studentService.getAllStudents();
        return ResponseEntity.ok().body(studentDtos);

    }


    //total count of the student
    @GetMapping("/getTotalStudentCount")
    public ResponseEntity<Long> getTotalStudentCount() {
        long totalStudentCount = studentService.getStudentCount();
        return ResponseEntity.ok(totalStudentCount);
    }


    @GetMapping("/rooms") // This annotation is used to map the HTTP GET requests onto specific handler methods.
    public ResponseEntity<?> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms()); // Returns a list of Room objects as JSON
    }

    @GetMapping("/room/{room_id}")
    // This annotation is used to map the HTTP GET requests onto specific handler methods.
    public ResponseEntity<?> getRoomByRoomNumber(@PathVariable String room_id) {
        return ResponseEntity.ok(roomService.getRoomByRoomNumber(room_id)); // Returns a Room object as JSON
    }

    // Add a new complaint
    @PostMapping("/addcomplaint")
    public void addComplaint(@RequestBody ComplainRequest complainRequest) { // This annotation is used to map the request body to the object
        // Add the logic to add a complaint

        try {
            complainService.addComplain(complainRequest); // Call the addComplain method in ComplainService
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    @GetMapping("/powerConsumption/{room_id}") // This annotation is used to map the HTTP GET requests onto specific handler methods.
    public ResponseEntity<?> getPowerConsumption(@PathVariable String room_id) {
        return ResponseEntity.ok(powerConsumptionService.getPowerConsumptionByRoomId(room_id)); // Returns a list of PowerConsumptionService objects as JSON
    }


}
