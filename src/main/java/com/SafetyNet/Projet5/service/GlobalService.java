package com.SafetyNet.Projet5.service;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.FireStationRepository;
import com.SafetyNet.Projet5.repository.MedicalRecordRepository;
import com.SafetyNet.Projet5.repository.PersonRepository;
import com.SafetyNet.Projet5.service.dto.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class GlobalService {

    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    private final int MAJORITY = 18;

    public GlobalService(PersonRepository personRepository, FireStationRepository fireStationRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.fireStationRepository = fireStationRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public ListPersonFireStationDTO personsFireStationByStationNumber(String stationNumber) {

        ListPersonFireStationDTO listPersonFireStationDTO = new ListPersonFireStationDTO();
        List<FireStation> fireStations = fireStationRepository.findAll();
        List<Person> persons = personRepository.findAll();

        Set<String> addresses = fireStations.stream().filter(fireStation -> fireStation.getStation().equals(stationNumber)).map(FireStation::getAddress).collect(Collectors.toSet());

        listPersonFireStationDTO.setPersonFireStationDTOList(new ArrayList<>());
        List<Person> peopleListFinded = new ArrayList<>();

        for (String address : addresses) {
            peopleListFinded.addAll(persons.stream().filter(person -> person.getAddress().equals(address)).collect(Collectors.toSet()));
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
                if (person.getFullName().equals(medicalRecord.getFullName()) && medicalRecord.getAge() > MAJORITY) {
                    result++;
                }
            }
        }
        return result;
    }

    private int nombreEnfants(int nombreAdultes, int nombreDePersonnes) {

        return nombreDePersonnes - nombreAdultes;
    }

    public ListChildAlertDTO childAlertByAddress(String address) {

        ListChildAlertDTO listChildAlertDTO = new ListChildAlertDTO();
        List<Person> persons = personRepository.findAll();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        listChildAlertDTO.setChildAlertDTOS(new ArrayList<>());


        List<Person> personByAddress = persons.stream()
                .filter(person -> person.getAddress().equals(address))
                .toList();

        List<MedicalRecord> medicalRecordMatching = medicalRecords.stream()
                .filter(medicalRecord -> personByAddress.stream()
                        .map(person -> person.getFullName())
                        .toList()
                        .contains(medicalRecord.getFullName()))
                .toList();

        List<MedicalRecord> childMedicalRecord = medicalRecordMatching.stream()
                .filter(medicalRecord -> medicalRecord.getAge() <= MAJORITY)
                .toList();

        List<MedicalRecord> otherMedicalRecord = medicalRecordMatching.stream()
                .filter(medicalRecord -> medicalRecord.getAge() > MAJORITY)
                .toList();

        for (MedicalRecord medicalRecord : childMedicalRecord) {
            PersonChildAlertDTO personChildAlertDTO = new PersonChildAlertDTO();
            personChildAlertDTO.setFirstName(medicalRecord.getFirstName());
            personChildAlertDTO.setLastName(medicalRecord.getLastName());
            personChildAlertDTO.setAge(medicalRecord.getAge());

            listChildAlertDTO.getChildAlertDTOS().add(personChildAlertDTO);
        }

        List<Person> otherMembers = new ArrayList<>();

        for (MedicalRecord medicalRecord : otherMedicalRecord) {
            Person person = new Person();
            person.setFirstName(medicalRecord.getFirstName());
            person.setLastName(medicalRecord.getLastName());
            person.setFirstName(medicalRecord.getFirstName());
            otherMembers.add(person);
        }

        listChildAlertDTO.setOtherMember(otherMembers);
        return listChildAlertDTO;

    }

    public ListPersonPhoneAlertByFireStationDTO phoneNumberByFireStationStationNumber(String stationNumber) {

        ListPersonPhoneAlertByFireStationDTO listPersonPhoneAlertByFireStationDTO = new ListPersonPhoneAlertByFireStationDTO();
        List<FireStation> fireStations = fireStationRepository.findAll();
        List<Person> persons = personRepository.findAll();
        listPersonPhoneAlertByFireStationDTO.setPhoneNumberFireStationDTOList(new ArrayList<>());

        Set<String> addresses = fireStations.stream().filter(fireStation -> fireStation.getStation().equals(stationNumber)).map(FireStation::getAddress).collect(Collectors.toSet());

        List<Person> phoneListFinded = new ArrayList<>();

        for (String address : addresses) {
            phoneListFinded.addAll(persons.stream().filter(person -> person.getAddress().equals(address)).collect(Collectors.toSet()));
        }

        for (Person person : phoneListFinded) {
            PersonPhoneAlertByFireStationDTO personPhoneAlertByFireStationDTO = new PersonPhoneAlertByFireStationDTO();
            personPhoneAlertByFireStationDTO.setPhone(person.getPhone());
            listPersonPhoneAlertByFireStationDTO.getPhoneNumberFireStationDTOList().add(personPhoneAlertByFireStationDTO);
        }

        return listPersonPhoneAlertByFireStationDTO;

    }

    public ListPersonFireByAddressDTO personByAddressWithMedicalRecord(String address) {

        ListPersonFireByAddressDTO listPersonFireByAddressDTO = new ListPersonFireByAddressDTO();
        listPersonFireByAddressDTO.setPersonFireAddressMedicalRecordDTOList(new ArrayList<>());

        List<Person> persons = personRepository.findAll();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        List<FireStation> fireStations = fireStationRepository.findAll();

        List<Person> personByAddress = persons.stream()
                .filter(person -> person.getAddress().equals(address))
                .toList();

        for (MedicalRecord medicalRecord : medicalRecords) {
            Person matchingPerson = personByAddress.stream()
                    .filter(person -> person.getFullName().equals(medicalRecord.getFullName()))
                    .findFirst()
                    .orElse(null);

            if (matchingPerson != null) {
                PersonFireByAddressDTO personFireByAddressDTO = new PersonFireByAddressDTO();
                personFireByAddressDTO.setLastName(medicalRecord.getLastName());
                personFireByAddressDTO.setAge(medicalRecord.getAge());
                personFireByAddressDTO.setAllergies(medicalRecord.getAllergies());
                personFireByAddressDTO.setMedications(medicalRecord.getMedications());
                personFireByAddressDTO.setPhone(matchingPerson.getPhone());

                listPersonFireByAddressDTO.getPersonFireAddressMedicalRecordDTOList().add(personFireByAddressDTO);
            }
        }

        String fireStationsFindStationByAddress = fireStations.stream()
                .filter(fireStation -> personByAddress.stream()
                        .anyMatch(person -> person.getAddress().equals(fireStation.getAddress())))
                .map(fireStation -> fireStation.getStation())
                .findFirst()
                .orElse(null);


        listPersonFireByAddressDTO.setStation(fireStationsFindStationByAddress);

        return listPersonFireByAddressDTO;
    }

    public ListPersonFloodStationSortByAddressDTO personByStationNumberSortedByAddress(List<String> stations) {

        ListPersonFloodStationSortByAddressDTO listPersonFloodStationSortByAddressDTO = new ListPersonFloodStationSortByAddressDTO();
        listPersonFloodStationSortByAddressDTO.setPersonSortByAddressByStationNumberDTOList(new ArrayList<>());
        List<Person> persons = personRepository.findAll();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        List<FireStation> fireStations = fireStationRepository.findAll();


        List<String> addresses = fireStations.stream()
                .filter(fireStation -> stations.contains(fireStation.getStation()))
                .map(FireStation::getAddress)
                .toList();

        List<Person> personByStationNumber = persons.stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .toList();

        for (MedicalRecord medicalRecord : medicalRecords) {
            Person matchingPerson = personByStationNumber.stream()
                    .filter(person -> person.getFullName().equals(medicalRecord.getFullName()))
                    .findFirst()
                    .orElse(null);

            if (matchingPerson != null) {
                PersonFloodStationSortByAddressDTO personFloodStationSortByAddressDTO = new PersonFloodStationSortByAddressDTO();
                personFloodStationSortByAddressDTO.setFirstName(medicalRecord.getFirstName());
                personFloodStationSortByAddressDTO.setLastName(medicalRecord.getLastName());
                personFloodStationSortByAddressDTO.setAge(medicalRecord.getAge());
                personFloodStationSortByAddressDTO.setAllergies(medicalRecord.getAllergies());
                personFloodStationSortByAddressDTO.setMedications(medicalRecord.getMedications());
                personFloodStationSortByAddressDTO.setPhone(matchingPerson.getPhone());
                personFloodStationSortByAddressDTO.setAddress(matchingPerson.getAddress());

                listPersonFloodStationSortByAddressDTO.getPersonSortByAddressByStationNumberDTOList().add(personFloodStationSortByAddressDTO);
            }
        }
        listPersonFloodStationSortByAddressDTO.getPersonSortByAddressByStationNumberDTOList()
                .sort(Comparator.comparing(PersonFloodStationSortByAddressDTO::getAddress));

        return listPersonFloodStationSortByAddressDTO;
    }

    public ListPersonInfoByLastNameDTO personInfoEachHabitant(String lastName) {

        ListPersonInfoByLastNameDTO listPersonInfoByLastNameDTO = new ListPersonInfoByLastNameDTO();
        listPersonInfoByLastNameDTO.setPersonNameByLastNameDTOList(new ArrayList<>());

        List<Person> persons = personRepository.findAll();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        List<Person> personByLastName = persons.stream()
                .filter(person -> lastName.contains(person.getLastName()))
                .toList();

        for (MedicalRecord medicalRecord : medicalRecords) {
            Person matchingPerson = personByLastName.stream()
                    .filter(person -> person.getFullName().equals(medicalRecord.getFullName()))
                    .findFirst()
                    .orElse(null);

            if (matchingPerson != null) {
                PersonInfoByLastNameDTO personInfoByLastNameDTO = new PersonInfoByLastNameDTO();
                personInfoByLastNameDTO.setLastName(medicalRecord.getLastName());
                personInfoByLastNameDTO.setFirstName(medicalRecord.getFirstName());
                personInfoByLastNameDTO.setAddress(matchingPerson.getAddress());
                personInfoByLastNameDTO.setAge(medicalRecord.getAge());
                personInfoByLastNameDTO.setEmail(matchingPerson.getEmail());
                personInfoByLastNameDTO.setMedications(medicalRecord.getMedications());
                personInfoByLastNameDTO.setAllergies(medicalRecord.getAllergies());

                listPersonInfoByLastNameDTO.getPersonNameByLastNameDTOList().add(personInfoByLastNameDTO);
            }

        }

        return listPersonInfoByLastNameDTO;
    }

    public List<String> PersonEmailByCity(String city) {

        List<Person> persons = personRepository.findAll();

        List<String> listpersonEmailByCity = persons.stream()
                .filter(person -> person.getCity().equals(city))
                .map(Person::getEmail)
                .toList();

        return listpersonEmailByCity;
}

}


