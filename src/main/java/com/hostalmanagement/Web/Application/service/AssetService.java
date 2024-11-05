package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.AssetDto;
import com.hostalmanagement.Web.Application.model.Asset;
import com.hostalmanagement.Web.Application.model.Student;
import com.hostalmanagement.Web.Application.repository.AssetRepository;
import com.hostalmanagement.Web.Application.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetService {


    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private AssetRepository assetRepository;

    // Add asset using a stored procedure and return a message
    public String saveAssetUsingProcedure(AssetDto assetDto) {
        // Check if student exists by tg_no
        if (studentRepo.existsByTgNo(assetDto.getTg_no())) {
            // Call stored procedure to insert asset
            assetRepository.insertAsset(
                    assetDto.getRoom_no(),
                    assetDto.getDescription(),
                    assetDto.getLocation(),
                    assetDto.getAcquisition_date(),
                    assetDto.getAsset_condition(),
                    assetDto.getTg_no()
            );
            return "Asset added successfully.";
        } else {
            return "Student not found with TG number: " + assetDto.getTg_no();
        }
    }

    public List<AssetDto> getAllAsset() {
        List<Asset> assetList = assetRepository.getAssetFromView();
        return assetList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private AssetDto convertToDto(Asset asset) {
        return new AssetDto(
                asset.getAsset_id(),
                asset.getRoom_no(),
                asset.getDescription(),
                asset.getLocation(),
                asset.getAcquisition_date(),
                asset.getAsset_condition(),
                asset.getStudent().getTg_no()
        );
    }




}