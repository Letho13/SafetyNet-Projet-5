package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fireStation")
public class FireStationController {

    private final static Logger LOG = LoggerFactory.getLogger(FireStationController.class);
    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping
    public List<FireStation> getAll() throws IOException {
        List<FireStation> fireStations = fireStationService.getAll();
        LOG.info("Successfully retrieved {} FireStations.", fireStations.size());
        return fireStations;
    }

    @GetMapping("/{address}")
    public ResponseEntity<FireStation> getByAddress(@PathVariable String address) {
        try {
            FireStation fireStation = fireStationService.getByAddress(address);
            LOG.info("Successfully retrieved FireStation for address: {}", address);
            return ResponseEntity.ok(fireStation);
        } catch (IllegalArgumentException e) {
                LOG.error("Error retrieving FireStation with address {}: {}", address, e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{address}")
    public ResponseEntity<FireStation> updateFireStation(@PathVariable String address, @RequestBody String updatedStation) {
               try {
           FireStation fireStation = fireStationService.update(address, updatedStation);
            LOG.info("Successfully updated FireStation for address: {}", fireStation);
            return ResponseEntity.ok(fireStation);
        } catch (IllegalArgumentException e){
            LOG.error("Update failed: FireStation with address {}: {} not found.", address, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/{address}")
    public ResponseEntity<Void> deleteByAddress(@PathVariable String address) {
        try {
            fireStationService.deleteByAddress(address);
            LOG.info("Successfully deleted FireStation for address: {}", address);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            LOG.error("Failed to delete FireStation for address: {}", address, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<FireStation> save(@RequestBody FireStation fireStation) {
        FireStation savedFireStation = fireStationService.save(fireStation);
        LOG.info("Successfully saved FireStation with address: {}", savedFireStation.getAddress());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFireStation);
    }
}
