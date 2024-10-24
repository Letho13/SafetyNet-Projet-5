package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.service.GlobalService;
import com.SafetyNet.Projet5.service.dto.ListPersonFireStationDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping
public class GlobalController {

    private final GlobalService globalService;

    public GlobalController(GlobalService globalService) {
        this.globalService = globalService;
    }

    @GetMapping("/firestation/{stationNumber}")
    public ListPersonFireStationDTO personsFireStationByStationNumber (@PathVariable String stationNumber) {
        return globalService.personsFireStationByStationNumber(stationNumber);
    }

}
