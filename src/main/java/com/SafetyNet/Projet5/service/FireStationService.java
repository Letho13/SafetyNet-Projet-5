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

    public FireStationService(FireStationRepository fireStationRepository){
        this.fireStationRepository = fireStationRepository;
    }

    public FireStation getByAddress(final String address){
        return fireStationRepository.findByAddress(address).orElse(null);
    }

    public List<FireStation> getAll() {
        return fireStationRepository.findAll();
    }

    public void deleteByAddress(final String address) {
        fireStationRepository.deleteByAddress(address);
    }

    public FireStation save(FireStation fireStation) {
        return fireStationRepository.save(fireStation);

    }



}
