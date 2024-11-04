package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;

    }

    @GetMapping()
    public List<MedicalRecord> getAll() throws IOException {
        return medicalRecordService.getAll();
    }

    @GetMapping("{medicalRecordName}")
    public MedicalRecord getByMedicalRecordName (@PathVariable String medicalRecordName){
        return medicalRecordService.getByMedicalRecordName(medicalRecordName);
    }

    @DeleteMapping("{medicalRecordName}")
    public void deleteByMedicalRecordName(@PathVariable String medicalRecordName){
        medicalRecordService.deleteByMedicalRecordName(medicalRecordName);
    }

    @PostMapping
    public MedicalRecord save(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.save(medicalRecord);
    }
}
