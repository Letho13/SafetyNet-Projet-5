package com.SafetyNet.Projet5.service;

import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord getByMedicalRecordName(final String medicalRecordName){
        return medicalRecordRepository.findByMedicalRecordName(medicalRecordName).orElse(null);
    }

    public List<MedicalRecord> getAll() {
        return medicalRecordRepository.findAll();
    }

    public void deleteByMedicalRecordName(final String medicalRecordName) {
        medicalRecordRepository.deleteByMedicalRecordName(medicalRecordName);
    }

    public MedicalRecord save(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

}
