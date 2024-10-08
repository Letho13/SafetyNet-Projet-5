package com.SafetyNet.Projet5.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.SafetyNet.Projet5.model.DataJson;
import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DataReaderUtil {

    private String URL_PATH = "C:\\DEV\\Repo\\Projet5\\src\\main\\resources\\data.json";


    public List<Person> getPersons() throws IOException {
        System.out.println(readDataJson().getPersons());
        return readDataJson().getPersons();
    }

    public List<FireStation> getFirestations() throws IOException {
        System.out.println(readDataJson().getFirestations());
        return readDataJson().getFirestations();
    }

    public List<MedicalRecord> getMedicalrecords() throws IOException {
        System.out.println(readDataJson().getMedicalrecords());
        return readDataJson().getMedicalrecords();
    }

    public DataJson readDataJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        File dataJsonfile = new File(URL_PATH);
        return objectMapper.readValue(dataJsonfile, DataJson.class);
    }

}