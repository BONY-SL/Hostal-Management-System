package com.hostalmanagement.Web.Application.controller;

import com.hostalmanagement.Web.Application.dto.AssetDto;
import com.hostalmanagement.Web.Application.model.Asset;
import com.hostalmanagement.Web.Application.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hostalmanage")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/addAsset")
    public ResponseEntity<Asset> addAsset(@RequestBody AssetDto assetDto){
        System.out.println(assetDto);
        Asset asset = assetService.addAsset(assetDto);
        return ResponseEntity.ok(asset);
    }
}
