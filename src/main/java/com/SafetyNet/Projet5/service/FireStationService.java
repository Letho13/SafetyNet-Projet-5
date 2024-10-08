package com.SafetyNet.Projet5.service;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;

    public Optional<FireStation> getFireStation(final String adress){
        return fireStationRepository.findByAdress(adress);
    }

    public List<FireStation> getFirestation() throws IOException{
        return fireStationRepository.findAll();
    }

    public void deleteFireStation(final String adress) {
        fireStationRepository.deleteByAdress(adress);
    }

    public FireStation saveFireStation(FireStation fireStation) {
        FireStation savedFireStation = fireStationRepository.save(fireStation);
        return savedFireStation;
    }
}
