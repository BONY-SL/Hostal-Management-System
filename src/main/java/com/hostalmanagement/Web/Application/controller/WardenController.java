package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.dto.NoticeDto;
import com.hostalmanagement.Web.Application.dto.StudentDto;
import com.hostalmanagement.Web.Application.model.Notice;
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

    //View Student
//    @GetMapping("/getStudent/warden")
//    public ResponseEntity<List<StudentDto>> getStudentDetails() {
//        System.out.println("Received request to retrieve student details");
//        List<StudentDto> studentDtos=studentService.getAllStudents();
//        return ResponseEntity.ok().body(studentDtos);
//
//    }

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
