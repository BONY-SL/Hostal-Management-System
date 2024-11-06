package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.dto.AssetDto;
import com.hostalmanagement.Web.Application.service.AssetService;
import com.hostalmanagement.Web.Application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/hostalmanage/dean")
public class DeanController {



    final private StudentService studentService;
    final private AssetService assetService;



    @Autowired
    public DeanController(StudentService studentService,AssetService assetService) {
        this.studentService = studentService;
        this.assetService=assetService;
    }

    //view student details
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudent(){

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    //view asset details
    @GetMapping("/getAssets")
    public ResponseEntity<List<AssetDto>>getAssetDetails(){
        List<AssetDto> assetDtos=assetService.getAllAsset();
        return ResponseEntity.ok().body(assetDtos);
    }


}
