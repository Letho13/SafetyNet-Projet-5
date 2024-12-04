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

    public Optional<MedicalRecord> findByName(String name){
        return medicalRecords.stream()
                .filter(medicalRecord -> (medicalRecord.getFirstName()+medicalRecord.getLastName()).equals(name))
                .findFirst();
    }

    public List<MedicalRecord> findAll() {
        return medicalRecords;
    }

    public void deleteByName(String name){
        medicalRecords = medicalRecords.stream()
                .filter(medicalRecord -> !(medicalRecord.getFirstName()+medicalRecord.getLastName()).equals(name))
                .toList();
    }

    public MedicalRecord updateMedicalRecord(String name, MedicalRecord updatedMedicalRecord) {
        for (int i = 0; i < medicalRecords.size(); i++) {
            MedicalRecord existingMedicalRecord = medicalRecords.get(i);
            if ((existingMedicalRecord.getFirstName() + existingMedicalRecord.getLastName()).equals(name)) {
                medicalRecords.set(i, updatedMedicalRecord);
                return updatedMedicalRecord;
            }
        }
        throw new IllegalArgumentException("Person not found");
    }

    public MedicalRecord save(MedicalRecord medicalRecord){
        medicalRecords.add(medicalRecord);
        return medicalRecord;
    }
}
