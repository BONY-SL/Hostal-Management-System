package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.dto.FineDto;
import com.hostalmanagement.Web.Application.model.Fine;
import com.hostalmanagement.Web.Application.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hrm")
public class FineController {

    @Autowired
    private FineService fineService;

    //add fine
    @PostMapping("/addFine")
    public ResponseEntity<Fine> addComplain(@RequestBody FineDto fineDto){
        System.out.println(fineDto);
        Fine fine=fineService.addFine(fineDto);
        return ResponseEntity.ok(fine);

    }


}
