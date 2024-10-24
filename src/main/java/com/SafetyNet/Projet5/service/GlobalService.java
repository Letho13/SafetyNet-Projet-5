package com.SafetyNet.Projet5.service;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.FireStationRepository;
import com.SafetyNet.Projet5.repository.MedicalRecordRepository;
import com.SafetyNet.Projet5.repository.PersonRepository;
import com.SafetyNet.Projet5.service.dto.ListPersonFireStationDTO;
import com.SafetyNet.Projet5.service.dto.PersonFireStationDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GlobalService {

    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    int majorite = 18;

    public GlobalService(PersonRepository personRepository, FireStationRepository fireStationRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.fireStationRepository = fireStationRepository;
        this.medicalRecordRepository = medicalRecordRepository;

    }

    public ListPersonFireStationDTO personsFireStationByStationNumber(String stationNumber) {

        ListPersonFireStationDTO listPersonFireStationDTO = new ListPersonFireStationDTO();
        List<FireStation> fireStations = fireStationRepository.findAll();
        List<Person> persons = personRepository.findAll();

        Set<String> addresses = fireStations.stream()
                .filter(fireStation -> fireStation.getStation().equals(stationNumber))
                .map(FireStation::getAddress)
                .collect(Collectors.toSet());

        listPersonFireStationDTO.setPersonFireStationDTOList(new ArrayList<>());
        List<Person> peopleListFinded = new ArrayList<>();

        for (String address : addresses) {
            peopleListFinded.addAll(persons.stream()
                    .filter(person -> person.getAddress().equals(address))
                    .collect(Collectors.toSet()));
        }

        for (Person person : peopleListFinded) {
            PersonFireStationDTO personFireStationDTO = new PersonFireStationDTO();
            personFireStationDTO.setFirstName(person.getFirstName());
            personFireStationDTO.setLastName(person.getLastName());
            personFireStationDTO.setAddress(person.getAddress());
            personFireStationDTO.setPhone(person.getPhone());


            listPersonFireStationDTO.getPersonFireStationDTOList().add(personFireStationDTO);
        }

        listPersonFireStationDTO.setNombreAdultes(nombreAdultes(peopleListFinded));
        listPersonFireStationDTO.setNombreEnfants(nombreEnfants(listPersonFireStationDTO.getNombreAdultes(), peopleListFinded.size()));

        return listPersonFireStationDTO;

    }

    private int nombreAdultes(@NotNull List<Person> persons) {

        int result = 0;

        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        for (Person person : persons) {
            for (MedicalRecord medicalRecord : medicalRecords) {
                if ((person.getFirstName() + person.getLastName()).equals(medicalRecord.getFirstName() + medicalRecord.getLastName())) {
                    String birthdateAconvertir = medicalRecord.getBirthdate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate birthdate = LocalDate.parse(birthdateAconvertir, formatter);

                    if (calculateAge(birthdate) > majorite) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private int nombreEnfants(int nombreAdultes, int nombreDePersonnes) {

        return nombreDePersonnes - nombreAdultes;
    }

    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        if (birthDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }


}


