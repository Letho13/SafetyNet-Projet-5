package com.SafetyNet.Projet5.service;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.FireStationRepository;
import com.SafetyNet.Projet5.repository.MedicalRecordRepository;
import com.SafetyNet.Projet5.repository.PersonRepository;
import com.SafetyNet.Projet5.service.dto.ListPersonFireStationDTO;
import com.SafetyNet.Projet5.service.dto.PersonBirthdateDTO;
import com.SafetyNet.Projet5.service.dto.PersonFireStationDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GlobalService {

    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public GlobalService(PersonRepository personRepository, FireStationRepository fireStationRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.fireStationRepository = fireStationRepository;
        this.medicalRecordRepository = medicalRecordRepository;

    }

    public ListPersonFireStationDTO personsFireStationByStationNumber(String stationNumber){

        ListPersonFireStationDTO listPersonFireStationDTO = new ListPersonFireStationDTO();
        List<FireStation> fireStations = fireStationRepository.findAll();
        List<Person> persons = personRepository.findAll();

        List<String> addresses = fireStations.stream()
                .filter(fireStation -> fireStation.getStation().equals(stationNumber))
                .map(FireStation::getAddress)
                .toList();

        List<Person> peopleListFinded = new ArrayList<>();

        for (String address : addresses) {
            List<Person> peoples = persons.stream()
                    .filter(person -> person.getAddress().equals(address))
                    .toList();
            peopleListFinded.addAll(peoples);

        }

        for (Person person: peopleListFinded) {
            PersonFireStationDTO personFireStationDTO = new PersonFireStationDTO();
            personFireStationDTO.setFirstName(person.getFirstName());
            personFireStationDTO.setLastName(person.getLastName());
            personFireStationDTO.setAddress(person.getAddress());
            personFireStationDTO.setPhone(person.getPhone());

            listPersonFireStationDTO.getPersonFireStationDTOList().add(personFireStationDTO);
        }

        return listPersonFireStationDTO;


    }
//private . Créer 2 autres methodes pour adultes et enfants. Verifier si adulte ou non et ajouter dans liste

    private ListPersonFireStationDTO NombreAdulte (int age){
        ListPersonFireStationDTO listPersonFireStationDTO = new ListPersonFireStationDTO();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        List<Person> persons = personRepository.findAll();

        List<PersonBirthdateDTO> personBirthdates = new ArrayList<>();

        for (PersonBirthdateDTO medicalRecord: personBirthdates) {
            PersonBirthdateDTO personBirthdatesDTO = new PersonBirthdateDTO();
            PersonBirthdateDTO.setBirthdate(medicalRecord.getBirthdate()); // Je comprend pas pq set Birthdate marche pas...
            personBirthdatesDTO.setMedicalRecordName(medicalRecord.getMedicalRecordName());


            personBirthdates.add(personBirthdatesDTO);

        }

        listPersonFireStationDTO.getNombreAdultes().add();

        return NombreAdulte(age);
    }


}
