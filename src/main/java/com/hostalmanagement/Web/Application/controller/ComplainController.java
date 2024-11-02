package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.dto.ComplainDto;
import com.hostalmanagement.Web.Application.model.Complain;
import com.hostalmanagement.Web.Application.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hrm")
public class ComplainController {


    @Autowired
    private ComplainService complainService;

    @PostMapping("/addComplain")
    public ResponseEntity<Complain> addComplain(@RequestBody ComplainDto complainDto){
        System.out.println(complainDto);
        Complain complain=complainService.addComplain(complainDto);
        return ResponseEntity.ok(complain);

    }
}
