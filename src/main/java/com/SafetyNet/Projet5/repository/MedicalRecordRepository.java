package com.SafetyNet.Projet5.repository;

import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.util.DataReaderUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MedicalRecordRepository {

    DataReaderUtil dataReaderUtil = new DataReaderUtil();
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    public MedicalRecordRepository(List<MedicalRecord> medicalRecords) throws IOException {
        this.medicalRecords = dataReaderUtil.getMedicalrecords();
    }

    public Optional<MedicalRecord> findByBirthdate(String birthdate){
        return medicalRecords.stream()
                .filter(medicalRecord -> medicalRecord.getBirthdate().equals(birthdate))
                .findFirst();
    }

    public List<MedicalRecord> findAll() {
        return medicalRecords;
    }

    public void deleteByBirthdate(String birthdate){
    }

    public MedicalRecord save(MedicalRecord medicalRecord){
        return null;
    }
}
