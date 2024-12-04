package com.SafetyNet.Projet5.repository;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.util.DataReaderUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FireStationRepository {

    DataReaderUtil dataReaderUtil = new DataReaderUtil();
    private List<FireStation> fireStations = new ArrayList<>();

    public FireStationRepository(List<FireStation> fireStations) throws IOException {
        this.fireStations = dataReaderUtil.getFirestations();
    }

    public Optional<FireStation> findByAddress(String address) {
        return fireStations.stream()
                .filter(fireStation -> fireStation.getAddress().equals(address))
                .findFirst();
    }

    public List<FireStation> findAll() {
        return fireStations;
    }

    public void deleteByAddress(String address) {
        fireStations = fireStations.stream()
                .filter(fireStation -> !fireStation.getAddress().equals(address))
                .toList();
    }

    public FireStation updateFireStation(String address, FireStation updatedStation) {
        for (int i = 0; i < fireStations.size(); i++) {
            FireStation existingStation = fireStations.get(i);
            if ((existingStation.getAddress().equals(address))) {
                fireStations.set(i, updatedStation);
                return updatedStation;
            }
        }
        throw new IllegalArgumentException("FireStation not found");
    }

    public FireStation save(FireStation fireStation) {
        fireStations.add(fireStation);
        return fireStation;
    }

}
