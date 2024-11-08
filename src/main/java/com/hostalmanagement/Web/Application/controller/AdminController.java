package com.hostalmanagement.Web.Application.controller;
import com.hostalmanagement.Web.Application.dto.*;
import com.hostalmanagement.Web.Application.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/hostalmanage/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class AdminController {


    private final AdminService adminService;


    @PostMapping("/createuser")
    public ResponseEntity<?> createNewUser(@RequestBody CreateUser createUser) {
        return adminService.createUser(createUser);
    }

    @GetMapping("/getSystemUsers")
    public ResponseEntity<List<UserDto>> getSystemUsers(){

        return ResponseEntity.ok(adminService.getSystemUsers());
    }

    @PostMapping("/savedstudentemail")
    public ResponseEntity<?> saveStudentEmailAndTgNumbers(@RequestBody List<StudentMailsStoreDTO> studentMailsStoreDTO){

        System.out.println(studentMailsStoreDTO);
        return adminService.saveStudentEmailAndTgNumbers(studentMailsStoreDTO);
    }


    @GetMapping("/getadmin")
    public ResponseEntity<GetAdminProfileDetails> getAdminProfileDetailsResponseEntity(
            @RequestParam Integer id) {

        return ResponseEntity.ok(adminService.getAdminProfileDetailsResponseEntity(id));
    }

    @PutMapping("/updateprofile")
    public ResponseEntity<?> updateAdminProfile(@RequestBody UpdateAdminDTO updateAdminDTO){

        return ResponseEntity.ok(adminService.updateAdminProfile(updateAdminDTO));

    }

    @PostMapping("/confirmold")
    public ResponseEntity<?> confirmationPassword(@RequestBody ChangePasswordRequest changePasswordRequest){

        System.out.println(changePasswordRequest.getId());
        System.out.println(changePasswordRequest.getCurrentPassword());
        return ResponseEntity.ok(adminService.confirmationPassword(changePasswordRequest));
    }


    @PutMapping("/updateps")
    public ResponseEntity<Map<String, String>> updatePassword(@RequestBody ChangePasswordRequest updatePasswordRequest) {
        boolean isUpdated = adminService.updatePassword(updatePasswordRequest);

        Map<String, String> response = new HashMap<>();
        if (isUpdated) {
            response.put("message", "Password updated successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Cannot update. The entered current password does not match.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/createRoom")
    public ResponseEntity<?> createNewRoom(@RequestBody CreateRoomRequest createRoomRequest) {

        String responseMessage = adminService.createNewRoom(createRoomRequest);
        return ResponseEntity.ok(Collections.singletonMap("message", responseMessage));

    }

}
