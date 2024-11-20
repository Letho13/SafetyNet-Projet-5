package com.SafetyNet.Projet5.Service;
import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.repository.FireStationRepository;
import com.SafetyNet.Projet5.service.FireStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FireStationServiceTest {

    @Mock
    private FireStationRepository fireStationRepository;

    @InjectMocks
    private FireStationService fireStationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByAddressReturnFireStationWhenAddressExists() {
        String address = "123 Main St";
        FireStation fireStation = new FireStation(address, "Station 1");
        when(fireStationRepository.findByAddress(address)).thenReturn(Optional.of(fireStation));
        FireStation result = fireStationService.getByAddress(address);
        assertEquals(fireStation, result);
        verify(fireStationRepository, times(1)).findByAddress(address);
    }

    @Test
    void getByAddressThrowExceptionWhenAddressDoesNotExist() {
        String address = "Non-existent address";
        when(fireStationRepository.findByAddress(address)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> fireStationService.getByAddress(address));
        verify(fireStationRepository, times(1)).findByAddress(address);
    }

    @Test
    void getAllReturnListOfFireStations() {
        FireStation fireStation1 = new FireStation("123 Main St", "Station 1");
        FireStation fireStation2 = new FireStation("456 Elm St", "Station 2");
        when(fireStationRepository.findAll()).thenReturn(List.of(fireStation1, fireStation2));
        List<FireStation> result = fireStationService.getAll();
        assertEquals(2, result.size());
        verify(fireStationRepository, times(1)).findAll();
    }

    @Test
    void deleteByAddressDeleteFireStationWhenAddressExists() {
        String address = "123 Main St";
        fireStationService.deleteByAddress(address);
        verify(fireStationRepository, times(1)).deleteByAddress(address);
    }

    @Test
    void deleteByAddressExceptionWhenRepositoryThrowsError() {
        String address = "123 Main St";
        doThrow(new RuntimeException("Database error")).when(fireStationRepository).deleteByAddress(address);
        fireStationService.deleteByAddress(address);
        verify(fireStationRepository, times(1)).deleteByAddress(address);
    }

    @Disabled
    @Test
    void updateReturnUpdatedFireStationWhenAddressExists() {
        String address = "123 Main St";
        String oldStationNumber = "1";
        String newStationNumber = "2";
        FireStation existingFireStation = new FireStation(oldStationNumber, address);
        FireStation updatedFireStation = new FireStation(newStationNumber, address);
        when(fireStationRepository.findByAddress(address)).thenReturn(Optional.of(existingFireStation));
        when(fireStationRepository.updateFireStation(newStationNumber, updatedFireStation)).thenReturn(updatedFireStation);
        FireStation result = fireStationService.update(address, newStationNumber);
        assertEquals(updatedFireStation, result);
        verify(fireStationRepository, times(1)).findByAddress(address);
        verify(fireStationRepository, times(1)).updateFireStation(address, updatedFireStation);
    }

    @Test
    void updateThrowExceptionWhenAddressDoesNotExist() {
        String address = "Non-existent address";
        FireStation updatedFireStation = new FireStation(address, "New Station");
        when(fireStationRepository.findByAddress(address)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> fireStationService.update(address,"2"));
        verify(fireStationRepository, times(1)).findByAddress(address);
    }

    @Test
    void saveReturnSavedFireStation() {
        FireStation fireStation = new FireStation("1", "123 Main Street");
        when(fireStationRepository.save(fireStation)).thenReturn(fireStation);
        FireStation result = fireStationService.save(fireStation);
        assertEquals(fireStation, result);
        verify(fireStationRepository, times(1)).save(fireStation);
    }
}

