package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.repository.PowerConsumptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PowerConsumptionService {

    // property
    private final PowerConsumptionRepository powerConsumptionRepository;

    // constructor
    public PowerConsumptionService(PowerConsumptionRepository powerConsumptionRepository) {
        this.powerConsumptionRepository = powerConsumptionRepository;
    }

    public List<Object[]> getPowerConsumptionByRoomId(String roomId) { // get power consumption by room id
        return  powerConsumptionRepository.getPowerConsumptionByRoomId(roomId); // return power consumption by room id
    }
}
