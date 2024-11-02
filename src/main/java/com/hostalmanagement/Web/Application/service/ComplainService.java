package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.ComplainDto;
import com.hostalmanagement.Web.Application.model.Complain;
import com.hostalmanagement.Web.Application.model.SubWarden;
import com.hostalmanagement.Web.Application.repository.ComplainRepository;
import com.hostalmanagement.Web.Application.repository.SubWardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.Optional;

@Service
public class ComplainService {
    @Autowired
    private ComplainRepository complainRepository;

    @Autowired
    private SubWardenRepository subWardenRepository;

    public Complain addComplain(ComplainDto complainDto) {

        SubWarden subWarden = subWardenRepository.findById(complainDto.getSubwarden_id())
                .orElseThrow(() -> new RuntimeException("SubWarden not found with id: " + complainDto.getSubwarden_id()));


        Complain complain = Complain.builder()
                .complain_id(complainDto.getComplain_id())
                .title(complainDto.getTitle())
                .description(complainDto.getDescription())
                .date_reported(complainDto.getDate_reported())
                .resolved_date(complainDto.getDate_resolved())
                .status(complainDto.getStatus())
                .subwarden(subWarden)
                .build();

        return complainRepository.save(complain);
    }
}
