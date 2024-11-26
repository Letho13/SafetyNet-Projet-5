package com.SafetyNet.Projet5.service;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.repository.FireStationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FireStationService {

    private final static Logger LOG = LoggerFactory.getLogger(FireStationService.class);

    private final FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    public FireStation getByAddress(final String address) {
        LOG.debug("Attempting to retrieve FireStation by address: {}", address);
        Optional<FireStation> byAddress = fireStationRepository.findByAddress(address);

        if (byAddress.isEmpty()) {
            LOG.error("FireStation not found for address: {}", address);
            throw new IllegalArgumentException("FireStation not found");
        }

        LOG.info("Successfully retrieved FireStation by address: {}", address);
        return byAddress.get();
    }

    public List<FireStation> getAll() {
        LOG.debug("Fetching all FireStations from the repository.");
        List<FireStation> stations = fireStationRepository.findAll();
        LOG.info("Successfully retrieved {} FireStations.", stations.size());
        return stations;
    }

    public void deleteByAddress(final String address) {
        LOG.debug("Attempting to delete FireStation by address: {}", address);
        try {
            fireStationRepository.deleteByAddress(address);
            LOG.info("Successfully deleted FireStation for address: {}", address);
        } catch (Exception e) {
            LOG.error("Failed to delete FireStation for address: {}", address, e);
        }
    }

    public FireStation update(final String address, final FireStation fireStation) {
        LOG.debug("Attempting to update FireStation for address: {}", address);

        FireStation existingFireStation = fireStationRepository.findByAddress(address)
                .orElseThrow(() -> new IllegalArgumentException("FireStation not found"));

        FireStation fireStationtoSave = getFireStation(fireStation, Optional.ofNullable(existingFireStation));
        FireStation savedFireStation = fireStationRepository.updateFireStation(address, fireStationtoSave);

        LOG.info("Successfully updated FireStation: {}", savedFireStation.getAddress());
        return savedFireStation;
    }

    private FireStation getFireStation(FireStation updatedFireStation, Optional<FireStation> existingFireStation) {
        FireStation fireStationToSave = existingFireStation.get();
        fireStationToSave.setStation(updatedFireStation.getStation());
        fireStationToSave.setAddress(updatedFireStation.getAddress());

        LOG.debug("Person fields successfully updated.");
        return fireStationToSave;
    }

    public FireStation save(FireStation fireStation) {
        LOG.debug("Attempting to save FireStation: {}", fireStation);

        FireStation savedStation = fireStationRepository.save(fireStation);
        LOG.info("Successfully saved FireStation with id: {}", savedStation);
        return savedStation;
    }

}
