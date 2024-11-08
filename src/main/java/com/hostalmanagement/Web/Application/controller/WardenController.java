package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.dto.*;
import com.hostalmanagement.Web.Application.model.Notice;
import com.hostalmanagement.Web.Application.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hostalmanage/warden")
@PreAuthorize("hasAuthority('WARDEN')")
@RequiredArgsConstructor
public class WardenController {
    private final StudentService studentService;
    private final NoticeService noticeService;
    private final FineService fineService;
    private final AssetService assetService;
    private final OutgoingService outgoingService;

    //View Student
    @GetMapping("/getStudent")
    public ResponseEntity<List<StudentDto>> getStudentDetails() {
        System.out.println("Received request to retrieve student details");
        List<StudentDto> studentDtos=studentService.getAllStudents();
      return ResponseEntity.ok().body(studentDtos);

 }

 //View Fines
    @GetMapping("/viewFines")
    public ResponseEntity<List<FineDto>> getFines() {
        System.out.println("Received request to retrieve fine details");
        List<FineDto> fineDtosDtos=fineService.getFinesByStudentId();
        return ResponseEntity.ok().body(fineDtosDtos);
    }

    //View Assets
    @GetMapping("/getAssets")
    public ResponseEntity<List<AssetDto>>getAssertDetails(){
        System.out.println("work");
        List<AssetDto> assetDtos=assetService.getAllAsset();
        return ResponseEntity.ok().body(assetDtos);
    }

    //View Outgoing Details
    @GetMapping("/getOutgoingDetails")
    public ResponseEntity<?>getAllOutgoingDetails(){
        System.out.println("Retrieving all Outgoing details");
        List<OutgoingDto> outgoingDtos=outgoingService.getAllOutgoings();
        return ResponseEntity.ok().body(outgoingDtos);
    }



    //Add new notices
    @PostMapping("/addNewNotice")
    /*public ResponseEntity<Notice> addNewNotice(@RequestBody NoticeDto noticeDto){
        Notice savedEntity=noticeService.saveNotice(noticeDto);
        return ResponseEntity.ok(savedEntity);
    }*/
    public ResponseEntity<String> addNotice(@RequestBody NoticeDto noticeDto) {
        try {
            noticeService.saveNotice(noticeDto);
            return ResponseEntity.ok("Notice added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add notice");
        }
    }

    //View notices
    @GetMapping("/viewNotices")
    public ResponseEntity<List<NoticeDto>>viewNotices(){
        List<NoticeDto> notice= noticeService.viewNotices();
        return ResponseEntity.ok().body(notice);
    }

    //Update notices
    @PutMapping("/updateNotices")
    public ResponseEntity<?>updateNotices(@RequestBody NoticeDto noticeDto){
        noticeService.updateNotices(noticeDto);
        return ResponseEntity.ok().build();
    }
}
