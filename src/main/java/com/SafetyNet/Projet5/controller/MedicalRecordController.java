package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/medicalRecord")
    public Iterable<MedicalRecord> getMedicalRecord() throws IOException {
        return medicalRecordService.getMedicalRecord();
    }

}
