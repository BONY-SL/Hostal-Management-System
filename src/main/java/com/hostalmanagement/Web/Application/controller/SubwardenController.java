package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.dto.*;
import com.hostalmanagement.Web.Application.model.Asset;
import com.hostalmanagement.Web.Application.model.Fine;
import com.hostalmanagement.Web.Application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/hostalmanage/subwarden")
@PreAuthorize("hasAuthority('SUB_WARDEN')")
public class SubwardenController {


    @Autowired
    private AssetService assetService;

    @Autowired
    private FineService fineService;

    @Autowired
    private OutgoingService outgoingService;


    @Autowired
    private StudentService studentService;

    @Autowired
    private RequestRoomService requestRoomService;


    //add fine
    @PostMapping("/addFine")
    public ResponseEntity<String> addComplain(@RequestBody FineDto fineDto){
        System.out.println("work");
        System.out.println(fineDto);
        String message=fineService.saveFineUsingProcedure(fineDto);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }



    //add asset
    @PostMapping("/addAsset")
    public ResponseEntity<String> createAsset(@RequestBody AssetDto assetDto) {
        System.out.println(assetDto);
        String message=assetService.saveAssetUsingProcedure(assetDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //get fine details
    @GetMapping("/getFine")
    public ResponseEntity<List<FineDto>> getFineDetails() {
        System.out.println("Received request to retrieve fine details");
        List<FineDto> fineDtosDtos=fineService.getFinesByStudentId();
        return ResponseEntity.ok().body(fineDtosDtos);

    }

    //get Asset details
    @GetMapping("/getAsset")
    public ResponseEntity<List<AssetDto>>getAssertDetails(){
        System.out.println("work");
        List<AssetDto> assetDtos=assetService.getAllAsset();
        return ResponseEntity.ok().body(assetDtos);
    }

    //get out goings
    @GetMapping("/getOutgoingDetails")
    public ResponseEntity<?>getAllOutgoingDetails(){
        System.out.println("Retrieving all Outgoing details");
        List<OutgoingDto> outgoingDtos=outgoingService.getAllOutgoings();
        return ResponseEntity.ok().body(outgoingDtos);
    }


    //total count of the student
    @GetMapping("/getTotalStudentCount")
    public ResponseEntity<Long> getTotalStudentCount() {
        long totalStudentCount = studentService.getStudentCount();
        return ResponseEntity.ok(totalStudentCount);
    }

    @GetMapping("/getFinepaymentDetails")
    public ResponseEntity<?>getFinepaymentDetails(){
        System.out.println("payment details");
        List<FinePaymentDto> finePaymentDtos=fineService.getFinePaymentDetails();
        return ResponseEntity.ok().body(finePaymentDtos);
    }

    @GetMapping("/getStudent")
    public ResponseEntity<List<StudentDto>> getStudentDetails() {
        System.out.println("Received request to retrieve student details");
        List<StudentDto> studentDtos=studentService.getAllStudents();
        return ResponseEntity.ok().body(studentDtos);

    }



    @GetMapping("/getrequestDetails")
    public ResponseEntity<?>getAllRequestDetails(){
        System.out.println("Retrieving all details");
        List<RequesrtDto> requesrtDtos=requestRoomService.getAllRequest();
        return ResponseEntity.ok().body(requesrtDtos);
    }


    @PutMapping("/updateRequestState")
    public ResponseEntity<String> updateRequestState(
            @RequestParam String tgNo,
            @RequestParam String newState) {
        String result = requestRoomService.updateRequestRoomState(tgNo, newState);
        return ResponseEntity.ok(result);
    }





}
