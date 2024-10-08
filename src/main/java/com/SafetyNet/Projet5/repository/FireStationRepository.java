package com.SafetyNet.Projet5.repository;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.util.DataReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<FireStation> findByAdress(String adress){
        return fireStations.stream()
                .filter(fireStation -> fireStation.getAddress().equals(adress))
                .findFirst();
    }

    public List<FireStation> findAll() {
        return fireStations;
    }

    public void deleteByAdress(String adress){
    }

    public FireStation save(FireStation fireStation){
        return null;
    }

}
