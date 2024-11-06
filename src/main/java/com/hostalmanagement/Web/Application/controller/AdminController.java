package com.hostalmanagement.Web.Application.controller;
import com.hostalmanagement.Web.Application.dto.CreateUser;
import com.hostalmanagement.Web.Application.dto.GetStudentStatusDTO;
import com.hostalmanagement.Web.Application.dto.StudentMailsStoreDTO;
import com.hostalmanagement.Web.Application.dto.UserDto;
import com.hostalmanagement.Web.Application.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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


}
