package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private final static Logger LOG = LoggerFactory.getLogger(MedicalRecordController.class);
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }


    @GetMapping()
    public List<MedicalRecord> getAll() {
        List<MedicalRecord> medicalRecords = medicalRecordService.getAll();
        LOG.info("Successfully retrieved {} medical records.", medicalRecords.size());
        return medicalRecords;
    }

    @GetMapping("/{name}")
    public ResponseEntity<MedicalRecord> getByMedicalRecordName(@PathVariable String name) {
        try {
            MedicalRecord medicalRecord = medicalRecordService.getByMedicalRecordName(name);
            LOG.info("Successfully retrieved medical record for: {}", name);
            return ResponseEntity.ok(medicalRecord);
        } catch (IllegalArgumentException e) {
            LOG.error("Error retrieving medical record with name {}: {}", name, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name) {
        try {
            medicalRecordService.deleteByName(name);
            LOG.info("Successfully deleted medical record with name: {}", name);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            LOG.error("Error deleting medical record with name {}: {}", name, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> save(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord savedMedicalRecord = medicalRecordService.save(medicalRecord);
        LOG.info("Successfully saved medical record with fullname: {}", savedMedicalRecord.getFullName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicalRecord);
    }

    @PutMapping("/{name}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable String name, @RequestBody MedicalRecord updatedMedicalRecord) {
        try {
            MedicalRecord medicalRecord = medicalRecordService.update(name, updatedMedicalRecord);
            LOG.info("Successfully updated medical record for: {}", name);
            return ResponseEntity.ok(medicalRecord);
        } catch (IllegalArgumentException e) {
            LOG.error("Error updating medical record with name {}: {}", name, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
