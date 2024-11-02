package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.FineDto;
import com.hostalmanagement.Web.Application.model.Fine;
import com.hostalmanagement.Web.Application.model.Student;
import com.hostalmanagement.Web.Application.repository.FineRepository;
import com.hostalmanagement.Web.Application.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FineService {

    @Autowired
    private FineRepository fineRepository;

    @Autowired
    private StudentRepo studentRepo;

    public Fine addFine(FineDto fineDto){
        Student student=studentRepo.findById(fineDto.getFine_id())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + fineDto.getStudentID()));

        Fine fine=Fine.builder()
                .amount(fineDto.getAmount())
                .reason(fineDto.getReason())
                .issuedDate(fineDto.getIssued_date())
                .status(fineDto.getStatus())
                .student(student)
                .build();
        return  fineRepository.save(fine);
    }


}
