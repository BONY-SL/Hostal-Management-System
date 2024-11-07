package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.RequesrtDto;
import com.hostalmanagement.Web.Application.dto.StudentDto;
import com.hostalmanagement.Web.Application.model.RequestRoom;
import com.hostalmanagement.Web.Application.model.Student;
import com.hostalmanagement.Web.Application.repository.RequestRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestRoomService {

    @Autowired
    private RequestRoomRepository requestRoomRepository;


    public List<RequesrtDto> getAllRequest() {
        List<RequestRoom> requesrtDtos = requestRoomRepository.getRequestRoomFromView();
        return requesrtDtos.stream().map(this::convertRequestToDTO).collect(Collectors.toList());
    }

    private RequesrtDto convertRequestToDTO(RequestRoom requestRoom) {
        return new RequesrtDto(
                requestRoom.getId(),
                requestRoom.getRoom_no(),
                requestRoom.getRequest_date(),
                requestRoom.getState(),
                requestRoom.getStudent().getTg_no()
        );
    }



}
