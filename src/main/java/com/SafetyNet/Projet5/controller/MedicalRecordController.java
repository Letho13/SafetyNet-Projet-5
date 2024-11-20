package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public List <MedicalRecord> getAll() {
            List<MedicalRecord> medicalRecords = medicalRecordService.getAll();
            LOG.info("Successfully retrieved {} medical records.", medicalRecords.size());
            return medicalRecords;
    }

    @GetMapping("/{medicalRecordName}")
    public ResponseEntity<MedicalRecord> getByMedicalRecordName(@PathVariable String medicalRecordName) {
        try {
            MedicalRecord medicalRecord = medicalRecordService.getByMedicalRecordName(medicalRecordName);
            LOG.info("Successfully retrieved medical record for: {}", medicalRecordName);
            return ResponseEntity.ok(medicalRecord);
        } catch (IllegalArgumentException e) {
            LOG.error("Error retrieving medical record with name {}: {}", medicalRecordName, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("{medicalRecordName}")
    public ResponseEntity<Void> deleteByMedicalRecordName(@PathVariable String medicalRecordName) {
        try {
            medicalRecordService.deleteByMedicalRecordName(medicalRecordName);
            LOG.info("Successfully deleted medical record with name: {}", medicalRecordName);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            LOG.error("Error deleting medical record with name {}: {}", medicalRecordName, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> save(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord savedMedicalRecord = medicalRecordService.save(medicalRecord);
        LOG.info("Successfully saved medical record with fullname: {}", savedMedicalRecord.getFullName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicalRecord);
    }

    @PutMapping("{medicalRecordName}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable String medicalRecordName, @RequestBody MedicalRecord updatedMedicalRecord) {
        try {
            MedicalRecord medicalRecord = medicalRecordService.update(medicalRecordName, updatedMedicalRecord);
            LOG.info("Successfully updated medical record for: {}", medicalRecordName);
            return ResponseEntity.ok(medicalRecord);
        } catch (IllegalArgumentException e) {
            LOG.error("Error updating medical record with name {}: {}", medicalRecordName, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
