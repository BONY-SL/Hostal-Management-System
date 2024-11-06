package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.dto.AssetDto;
import com.hostalmanagement.Web.Application.dto.FineDto;
import com.hostalmanagement.Web.Application.dto.NoticeDto;
import com.hostalmanagement.Web.Application.dto.StudentDto;
import com.hostalmanagement.Web.Application.model.Notice;
import com.hostalmanagement.Web.Application.service.AssetService;
import com.hostalmanagement.Web.Application.service.FineService;
import com.hostalmanagement.Web.Application.service.NoticeService;
import com.hostalmanagement.Web.Application.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hostalmanage")
@CrossOrigin("*")
@RequiredArgsConstructor
public class WardenController {
    private final StudentService studentService;
    private final NoticeService noticeService;
    private final FineService fineService;
    private final AssetService assetService;

    //View Student
    @GetMapping("/getStudent/warden")
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



    //Add new notices
    @PostMapping("/addNewNotice")
    public ResponseEntity<Notice> addNewNotice(@RequestBody NoticeDto noticeDto){
        Notice savedEntity=noticeService.saveNotice(noticeDto);
        return ResponseEntity.ok(savedEntity);
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
