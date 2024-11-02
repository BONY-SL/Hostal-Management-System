package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.OutgoingDto;
import com.hostalmanagement.Web.Application.model.OutgoingDetails;
import com.hostalmanagement.Web.Application.repository.OutgoingDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutgoingService {

    private final OutgoingDetailsRepo outgoingDetailsRepo;

    public List<OutgoingDto> getAllOutgoings() {
        List<OutgoingDetails> outgoingList = outgoingDetailsRepo.getOutgoingFromView();
        return outgoingList.stream().map(this::covertOutgoingToDto).collect(Collectors.toList());
    }

    private OutgoingDto covertOutgoingToDto(OutgoingDetails outgoingDetails) {
        return OutgoingDto.builder()
                .outgoingId(outgoingDetails.getOutgoingId())
                .studentName(outgoingDetails.getStudentName())
                .location(outgoingDetails.getLocation())
                .date(outgoingDetails.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .arrivalTime(outgoingDetails.getArrivalTime().toLocalTime())  // Convert from java.sql.Time to LocalTime
                .leaveTime(outgoingDetails.getLeaveTime().toLocalTime())
                .build();
    }
}
