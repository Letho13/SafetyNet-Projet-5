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

    public MedicalRecord getByMedicalRecordName(final String medicalRecordName) {
        LOG.debug("Received request to get medical record by name: {}", medicalRecordName);
        Optional<MedicalRecord> medicalRecordOpt = medicalRecordRepository.findByMedicalRecordName(medicalRecordName);
        if (medicalRecordOpt.isEmpty()) {
            LOG.error("Medical record with name {} not found.", medicalRecordName);
        } else {
            LOG.info("Successfully retrieved medical record with name: {}", medicalRecordName);
        }
        return medicalRecordOpt.orElse(null);
    }

    public List<MedicalRecord> getAll() {
        LOG.debug("Received request to retrieve all medical records.");
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        LOG.info("Successfully retrieved {} medical records.", medicalRecords.size());
        return medicalRecords;
    }

    public void deleteByMedicalRecordName(final String medicalRecordName) {
        LOG.debug("Received request to delete medical record by name: {}", medicalRecordName);
        try {
            medicalRecordRepository.deleteByMedicalRecordName(medicalRecordName);
            LOG.info("Successfully deleted medical record with name: {}", medicalRecordName);
        } catch (Exception e) {
            LOG.error("Error deleting medical record with name {}: {}", medicalRecordName, e.getMessage());
        }
    }

    public MedicalRecord save(MedicalRecord medicalRecord) {
        LOG.debug("Received request to save medical record: {}", medicalRecord);
        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);
        LOG.info("Successfully saved medical record with FullName: {}", savedMedicalRecord.getFullName());
        return savedMedicalRecord;
    }

    public MedicalRecord update(final String fullName, final MedicalRecord updatedMedicalRecord) {
        LOG.debug("Received request to update medical record for FullName: {}", fullName);

        Optional<MedicalRecord> existingMedicalRecordOpt = medicalRecordRepository.findByMedicalRecordName(fullName);
        if (existingMedicalRecordOpt.isEmpty()) {
            LOG.error("Update failed: Medical record with FullName {} not found.", fullName);
            throw new IllegalArgumentException("Medical record not found");
        }

        MedicalRecord existingMedicalRecord = existingMedicalRecordOpt.get();
        existingMedicalRecord.setFirstName(updatedMedicalRecord.getFirstName());
        existingMedicalRecord.setLastName(updatedMedicalRecord.getLastName());
        existingMedicalRecord.setBirthdate(updatedMedicalRecord.getBirthdate());
        existingMedicalRecord.setAllergies(updatedMedicalRecord.getAllergies());
        existingMedicalRecord.setMedications(updatedMedicalRecord.getMedications());


        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(existingMedicalRecord);
        LOG.info("Successfully updated medical record for FullName: {}", savedMedicalRecord.getFullName());
        return savedMedicalRecord;
    }
}