package com.SafetyNet.Projet5.service;

import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.repository.MedicalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    private final static Logger LOG = LoggerFactory.getLogger(MedicalRecordService.class);
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord getByMedicalRecordName(final String name) {
        LOG.debug("Received request to get medical record by name: {}", name);
        Optional<MedicalRecord> medicalRecordOpt = medicalRecordRepository.findByName(name);
        if (medicalRecordOpt.isEmpty()) {
            LOG.error("Medical record with name {} not found.", name);
        } else {
            LOG.info("Successfully retrieved medical record with name: {}", name);
        }
        return medicalRecordOpt.orElse(null);
    }

    public List<MedicalRecord> getAll() {
        LOG.debug("Received request to retrieve all medical records.");
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        LOG.info("Successfully retrieved {} medical records.", medicalRecords.size());
        return medicalRecords;
    }

    public void deleteByName(final String name) {
        LOG.debug("Received request to delete medical record by name: {}", name);
        try {
            medicalRecordRepository.deleteByName(name);
            LOG.info("Successfully deleted medical record with name: {}", name);
        } catch (Exception e) {
            LOG.error("Error deleting medical record with name {}: {}", name, e.getMessage());
        }
    }

    public MedicalRecord update(final String name, final MedicalRecord updatedMedicalRecord) {
        LOG.debug("Received request to update medical record for FullName: {}", name);

        MedicalRecord existingMedicalRecord = medicalRecordRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Medical record not found"));

        MedicalRecord medicalRecordToSave = getMedicalRecord(updatedMedicalRecord, Optional.ofNullable(existingMedicalRecord));
        MedicalRecord savedMedicalRecord = medicalRecordRepository.updateMedicalRecord(name, medicalRecordToSave);

        LOG.info("Successfully updated medical record for FullName: {}", savedMedicalRecord.getFullName());
        return savedMedicalRecord;
    }

    public MedicalRecord save(MedicalRecord medicalRecord) {
        LOG.debug("Received request to save medical record: {}", medicalRecord);
        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);

        LOG.info("Successfully saved medicalRecord with FullName: {}", savedMedicalRecord);
        return savedMedicalRecord;
    }

    private static MedicalRecord getMedicalRecord(MedicalRecord updatedMedicalRecord, Optional<MedicalRecord> existingMedicalRecord) {
        LOG.debug("Updating fields for Person based on provided updated information.");
        MedicalRecord medicalRecordToSave = existingMedicalRecord.get();
        medicalRecordToSave.setFirstName(updatedMedicalRecord.getFirstName());
        medicalRecordToSave.setLastName(updatedMedicalRecord.getLastName());
        medicalRecordToSave.setBirthdate(updatedMedicalRecord.getBirthdate());
        medicalRecordToSave.setMedications(updatedMedicalRecord.getMedications());
        medicalRecordToSave.setAllergies(updatedMedicalRecord.getAllergies());

        LOG.debug("Person fields successfully updated.");
        return medicalRecordToSave;
    }

}