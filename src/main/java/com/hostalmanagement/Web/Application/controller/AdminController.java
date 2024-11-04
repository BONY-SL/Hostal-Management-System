package com.hostalmanagement.Web.Application.controller;
import com.hostalmanagement.Web.Application.dto.CreateUser;
import com.hostalmanagement.Web.Application.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
