package com.hostalmanagement.Web.Application.controller;


import com.hostalmanagement.Web.Application.dto.OutgoingDto;
import com.hostalmanagement.Web.Application.service.OutgoingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hostalmanage")
@RequiredArgsConstructor
public class OutgoingController {
    private final OutgoingService outgoingService;

    @GetMapping("/getOutgoingDetails")
    public ResponseEntity<?>getAllOutgoingDetails(){
        System.out.println("Retrieving all Outgoing details");
        List<OutgoingDto> outgoingDtos=outgoingService.getAllOutgoings();
        return ResponseEntity.ok().body(outgoingDtos);
    }
}
