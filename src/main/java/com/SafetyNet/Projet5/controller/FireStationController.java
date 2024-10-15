package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FireStationController {

    @Autowired
    private FireStationService fireStationService;

    @GetMapping("/fireStations")
    public Iterable<FireStation> getFireStation() throws IOException {
        return fireStationService.getFirestation();
    }

}
