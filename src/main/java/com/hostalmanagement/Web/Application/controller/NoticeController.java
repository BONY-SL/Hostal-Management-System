package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.dto.NoticeDto;
import com.hostalmanagement.Web.Application.model.Notice;
import com.hostalmanagement.Web.Application.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hostalmanage")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

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

    //Delete notices
   /* @PatchMapping("/deleteNotices/{id}")
    public ResponseEntity<?>deleteNotices(@PathVariable int id){

    }*/

}
