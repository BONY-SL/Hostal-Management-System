package com.hostalmanagement.Web.Application.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@RequestMapping("/hostalmanage")
@RequiredArgsConstructor
public class AdminController {

    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(){

        return ResponseEntity.ok(null);

    }


}
