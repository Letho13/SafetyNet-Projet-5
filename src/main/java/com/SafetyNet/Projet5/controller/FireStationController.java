package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.service.FireStationService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fireStations")
public class FireStationController {

    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;

    }

    @GetMapping
    public List<FireStation> getAll() throws IOException {
        return fireStationService.getAll();
    }

    @GetMapping("{address}")
    public FireStation getByAddress(@PathVariable String address){
        return fireStationService.getByAddress(address);
    }

    @DeleteMapping("{address}")
    public void deleteByAddress(@PathVariable String address){
        fireStationService.deleteByAddress(address);
    }

    @PostMapping
    public FireStation save(@RequestBody FireStation fireStation){
        return fireStationService.save(fireStation);
    }

}
