package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.dto.AssetDto;
import com.hostalmanagement.Web.Application.dto.FineDto;
import com.hostalmanagement.Web.Application.model.Asset;
import com.hostalmanagement.Web.Application.model.Fine;
import com.hostalmanagement.Web.Application.service.AssetService;
import com.hostalmanagement.Web.Application.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/hostalmanage/subwarden")
public class SubwardenController {


    @Autowired
    private AssetService assetService;

    @Autowired
    private FineService fineService;

/*
    //add fine
    @PostMapping("/addFine")
    public ResponseEntity<String> addComplain(@RequestBody FineDto fineDto){
        System.out.println("work");
        System.out.println(fineDto);
        String message=fineService.saveFineUsingProcedure(fineDto);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

 */

    //add asset
    @PostMapping("/addAsset")
    public ResponseEntity<String> createAsset(@RequestBody AssetDto assetDto) {
        System.out.println(assetDto);
        String message=assetService.saveAssetUsingProcedure(assetDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    /*
    //get fine details
    @GetMapping("/getFine")
    public ResponseEntity<List<FineDto>> getStudentDetails() {
        System.out.println("Received request to retrieve fine details");
        List<FineDto> fineDtosDtos=fineService.getFinesByStudentId();
        return ResponseEntity.ok().body(fineDtosDtos);

    }


     */

    //get Asset details
    @GetMapping("/getAsset")
    public ResponseEntity<List<AssetDto>>getAssertDetails(){
        System.out.println("work");
        List<AssetDto> assetDtos=assetService.getAllAsset();
        return ResponseEntity.ok().body(assetDtos);
    }


}
