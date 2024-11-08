package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.FineDto;
import com.hostalmanagement.Web.Application.dto.FinePaymentDto;
import com.hostalmanagement.Web.Application.model.Fine;
import com.hostalmanagement.Web.Application.model.PaymentUpdatesLog;
import com.hostalmanagement.Web.Application.repository.FineRepository;
import com.hostalmanagement.Web.Application.repository.FinepaymentRepository;
import com.hostalmanagement.Web.Application.repository.StudentRepo;
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

    @Autowired
    private FinepaymentRepository finepaymentRepository;

    public String saveFineUsingProcedure(FineDto fineDto) {
        // Check if the student exists
        if (studentRepo.existsByTgNo(fineDto.getTg_no())) {
            // Call the repository to insert the fine
            fineRepository.insertFine(
                    fineDto.getAmount(),
                    fineDto.getReason(),
                    fineDto.getIssued_date(),
                    fineDto.getFine_status(),
                    fineDto.getTg_no()
            );
            return "Fine added successfully.";
        } else {
            // Return error message if the student is not found
            return "Student not found with ID: " + fineDto.getTg_no();
        }
    }


    // This method will run every 60 minutes
    @Scheduled(fixedRate = 3600000)
    public void updateFines() {
        fineRepository.updateFineStatusAndAmount();
    }



    public List<FineDto> getFinesByStudentId() {
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
                fine.getStudent().getTg_no()
        );
    }

    public List<FinePaymentDto>getFinePaymentDetails(){
        List<PaymentUpdatesLog>fineList=finepaymentRepository.getfinePaymentDetails();
        return fineList.stream().map(this::convertToFinepaymentDto).collect(Collectors.toList());
    }

    private FinePaymentDto convertToFinepaymentDto(PaymentUpdatesLog paymentUpdatesLog){
        return  new FinePaymentDto(
                paymentUpdatesLog.getTgNo(),
                paymentUpdatesLog.getIssueDate(),
                paymentUpdatesLog.getUpdateTime(),
                paymentUpdatesLog.getMessage()
        );


    }




}
