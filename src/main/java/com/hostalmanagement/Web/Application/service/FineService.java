package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.FineDto;
import com.hostalmanagement.Web.Application.model.Fine;
import com.hostalmanagement.Web.Application.repository.FineRepository;
import com.hostalmanagement.Web.Application.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FineService {

    @Autowired
    private FineRepository fineRepository;

    @Autowired
    private StudentRepo studentRepo;

   @Transactional
    public String saveFineUsingProcedure(FineDto fineDto) {
        // Check if the student exists
        if (studentRepo.existsById(fineDto.getStudentID())) {
            // Call the repository to insert the fine
            fineRepository.insertFine(
                    fineDto.getAmount(),
                    fineDto.getReason(),
                    fineDto.getIssued_date(),
                    fineDto.getFine_status(),
                    fineDto.getStudentID()
            );
            return "Fine added successfully.";
        } else {
            // Return error message if the student is not found
            return "Student not found with ID: " + fineDto.getStudentID();
        }
    }

    // This method will run every 60 minutes
    @Scheduled(fixedRate = 3600000)
    public void updateFines() {
        fineRepository.updateFineStatusAndAmount();
    }

    public List<FineDto> getALLFines() {
        List<Fine> fineList = fineRepository.getFineFromView();
        return fineList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Helper method to convert Fine entity to FineDto
    private FineDto convertToDto(Fine fine) {
        return new FineDto(
                fine.getFineId(),
                fine.getAmount(),
                fine.getReason(),
                fine.getIssuedDate(),
                fine.getFine_status(),
                fine.getStudent().getFirstName(),
                fine.getStudent().getStudentID()
        );
    }

}
