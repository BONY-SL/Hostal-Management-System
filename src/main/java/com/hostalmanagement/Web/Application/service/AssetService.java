package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.AssetDto;
import com.hostalmanagement.Web.Application.model.Asset;
import com.hostalmanagement.Web.Application.model.Student;
import com.hostalmanagement.Web.Application.repository.AssetRepository;
import com.hostalmanagement.Web.Application.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private AssetRepository assetRepository;

    //add asset
    public Asset addAsset(AssetDto assetDto) {

        Student student = studentRepo.findById(assetDto.getStudentID())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + assetDto.getStudentID()));

        Asset asset = Asset.builder()
                .room_no(assetDto.getRoom_no())
                .assetCondition(assetDto.getCondition())
                .acquisition_date(assetDto.getAcquisition_date())
                .description(assetDto.getDescription())
                .location(assetDto.getLocation())
                .student(student)
                .build();

        return assetRepository.save(asset);
    }
}
