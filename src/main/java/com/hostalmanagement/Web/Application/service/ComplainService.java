package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.ComplainRequest;
import com.hostalmanagement.Web.Application.model.Complain;
import com.hostalmanagement.Web.Application.model.Student;
import com.hostalmanagement.Web.Application.repository.ComplainRepository;
import com.hostalmanagement.Web.Application.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional // This annotation is used to configure the transaction on the service class
public class ComplainService {

    // Autowire the ComplainRepository
    final private ComplainRepository complainRepository;
    // Autowire the StudentRepo
    final  private StudentRepo studentRepo;

    // Constructor
    public ComplainService(ComplainRepository complainRepository, StudentRepo studentRepo) {
        this.complainRepository = complainRepository;
        this.studentRepo = studentRepo;
    }

    // Add a new complaint
    public void addComplain(ComplainRequest complainRequest) throws Exception {
        // Add the logic to add a complaint
        Complain complain = new Complain();

        // Set the values to the Complaint object
        complain.setRoomNumber(complainRequest.getRoomNumber());
        complain.setComplainType(complainRequest.getComplaintType());
        complain.setDescription(complainRequest.getDescription());
        complain.setContactNumber(complainRequest.getContact());
        complain.setStatus(complainRequest.getStatus());

        // Fetch the Student by studentId
        Student student =  studentRepo.findById(complainRequest.getStudentId()).
                orElseThrow(() -> new Exception("Menu not found"));
        // Set the Student in Complaint
        complain.setStudent(student);
        // Save the Complaint
        complainRepository.save(complain);

    }
}
