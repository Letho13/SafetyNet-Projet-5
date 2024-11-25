package com.SafetyNet.Projet5.Service;

import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.repository.MedicalRecordRepository;
import com.SafetyNet.Projet5.service.MedicalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByMedicalRecordNameFound() {

        MedicalRecord mockRecord = new MedicalRecord("John", "Doe", "01/01/1980",
                Arrays.asList("Med1"), Arrays.asList("Allergy1"));
        when(medicalRecordRepository.findByName("JohnDoe")).thenReturn(Optional.of(mockRecord));

        MedicalRecord result = medicalRecordService.getByMedicalRecordName("JohnDoe");
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(medicalRecordRepository, times(1)).findByName("JohnDoe");
    }

    @Test
    void testGetByMedicalRecordNameNotFound() {

        when(medicalRecordRepository.findByName("JaneDoe")).thenReturn(Optional.empty());
        MedicalRecord result = medicalRecordService.getByMedicalRecordName("JaneDoe");

        assertNull(result);

        verify(medicalRecordRepository, times(1)).findByName("JaneDoe");
    }

    @Test
    void testGetAllMedicalRecords() {

        List<MedicalRecord> mockRecords = Arrays.asList(
                new MedicalRecord("John", "Doe", "01/01/1980", Arrays.asList("Med1"), Arrays.asList("Allergy1")),
                new MedicalRecord("Jane", "Smith", "02/02/1990", Arrays.asList("Med2"), Arrays.asList("Allergy2"))
        );
        when(medicalRecordRepository.findAll()).thenReturn(mockRecords);

        List<MedicalRecord> result = medicalRecordService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(medicalRecordRepository, times(1)).findAll();
    }

    @Test
    void testDeleteByMedicalRecordName() {

        medicalRecordService.deleteByName("JohnDoe");
        verify(medicalRecordRepository, times(1)).deleteByName("JohnDoe");
    }

    @Test
    void testSaveMedicalRecord() {

        MedicalRecord mockRecord = new MedicalRecord("John", "Doe", "01/01/1980",
                Arrays.asList("Med1"), Arrays.asList("Allergy1"));
        when(medicalRecordRepository.save(mockRecord)).thenReturn(mockRecord);

        MedicalRecord result = medicalRecordService.save(mockRecord);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(medicalRecordRepository, times(1)).save(mockRecord);
    }

    @Test
    void testUpdateMedicalRecord_Success() {

        MedicalRecord existingRecord = new MedicalRecord("John", "Doe", "01/01/1980",
                Arrays.asList("Med1"), Arrays.asList("Allergy1"));
        MedicalRecord updatedRecord = new MedicalRecord("John", "Doe", "01/01/1985",
                Arrays.asList("Med2"), Arrays.asList("Allergy2"));

        when(medicalRecordRepository.findByName("JohnDoe")).thenReturn(Optional.of(existingRecord));
        when(medicalRecordRepository.updateMedicalRecord(eq("JohnDoe"),any(MedicalRecord.class))).thenReturn(updatedRecord);

        MedicalRecord result = medicalRecordService.update("JohnDoe", updatedRecord);

        assertNotNull(result);
        assertEquals("01/01/1985", result.getBirthdate());
        assertEquals(Arrays.asList("Med2"), result.getMedications());
        assertEquals(Arrays.asList("Allergy2"), result.getAllergies());
        verify(medicalRecordRepository, times(1)).findByName("JohnDoe");
    }

    @Test
    void testUpdateMedicalRecord_NotFound() {

        MedicalRecord updatedRecord = new MedicalRecord("John", "Doe", "01/01/1985",
                Arrays.asList("Med2"), Arrays.asList("Allergy2"));

        when(medicalRecordRepository.findByName("JohnDoe")).thenReturn(Optional.empty());


        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                medicalRecordService.update("JohnDoe", updatedRecord));
        assertEquals("Medical record not found", exception.getMessage());
    }
}
