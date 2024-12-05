package com.SafetyNet.Projet5.Service;

import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.FireStationRepository;
import com.SafetyNet.Projet5.repository.MedicalRecordRepository;
import com.SafetyNet.Projet5.repository.PersonRepository;
import com.SafetyNet.Projet5.service.GlobalService;
import com.SafetyNet.Projet5.service.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GlobalServiceTest {

    @InjectMocks
    private GlobalService globalService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private FireStationRepository fireStationRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        globalService = new GlobalService(personRepository, fireStationRepository, medicalRecordRepository);
    }

    @Test
    void childAlertByAddressTest() {

        String address = "123 test";
        Person person1 = new Person("John", "Doe", "123 test", "city", 12345, "032323", "email@test.com");
        Person person2 = new Person("Jane", "Doe", "123 test", "city", 12345, "032323", "email@test.org");

        MedicalRecord medicalRecord1 = new MedicalRecord("John", "Doe", "03/03/1989");
        MedicalRecord medicalRecord2 = new MedicalRecord("Jane", "Doe", "03/03/2020");

        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));
        when(medicalRecordRepository.findAll()).thenReturn(Arrays.asList(medicalRecord1, medicalRecord2));

        ListChildAlertDTO result = globalService.childAlertByAddress(address);

        assertNotNull(result);
        assertEquals(1, result.getChildAlertDTOS().size());
        assertEquals(1, result.getOtherMember().size());

        assertEquals("John", result.getOtherMember().getFirst().getFirstName());
        assertEquals("Doe", result.getOtherMember().getFirst().getLastName());

        assertEquals("Jane", result.getChildAlertDTOS().getFirst().getFirstName());
        assertEquals("Doe", result.getChildAlertDTOS().getFirst().getLastName());
        assertEquals(4, result.getChildAlertDTOS().getFirst().getAge());

    }

    @Test
    void personsFireStationByStationNumberTest() {

        String stationNumber = "3";

        FireStation fireStation1 = new FireStation("3", "123 test");

        Person person1 = new Person("John", "Doe", "123 test", "city", 12345, "032323", "email@test.com");
        Person person2 = new Person("Jane", "Doe", "123 test", "city", 12345, "032323", "email@test.org");

        MedicalRecord medicalRecord1 = new MedicalRecord("John", "Doe", "03/03/1989");
        MedicalRecord medicalRecord2 = new MedicalRecord("Jane", "Doe", "03/03/2020");

        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));
        when(fireStationRepository.findAll()).thenReturn(List.of(fireStation1));
        when(medicalRecordRepository.findAll()).thenReturn(Arrays.asList(medicalRecord1, medicalRecord2));

        ListPersonFireStationDTO result = globalService.personsFireStationByStationNumber(stationNumber);

        assertNotNull(result);
        assertEquals(1, result.getNombreAdultes());
        assertEquals(1, result.getNombreEnfants());

        assertEquals(2, result.getPersonFireStationDTOList().size());

    }

    @Test
    void personByStationNumberSortedByAddressTest() {

        List<String> stations = Arrays.asList("3", "2");

        FireStation fireStation1 = new FireStation("3", "123 test");
        FireStation fireStation2 = new FireStation("2", "543 test");

        Person person1 = new Person("John", "Doe", "123 test", "city", 12345, "032323", "email@test.com");
        Person person2 = new Person("Jane", "Doe", "123 test", "city", 12345, "032323", "email@test.org");
        Person person3 = new Person("Joe", "Smith", "543 test", "city", 12345, "032323", "email@test.org");

        MedicalRecord medicalRecord1 = new MedicalRecord("John", "Doe", "03/03/1989");
        MedicalRecord medicalRecord2 = new MedicalRecord("Jane", "Doe", "03/03/2020");
        MedicalRecord medicalRecord3 = new MedicalRecord("Joe", "Smith", "03/04/2018");

        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2, person3));
        when(fireStationRepository.findAll()).thenReturn(List.of(fireStation1, fireStation2));
        when(medicalRecordRepository.findAll()).thenReturn(Arrays.asList(medicalRecord1, medicalRecord2, medicalRecord3));

        ListPersonFloodStationSortByAddressDTO result = globalService.personByStationNumberSortedByAddress(stations);

        assertNotNull(result);

        assertEquals("John", result.getPersonSortByAddressByStationNumberDTOList().get(0).getFirstName());
        assertEquals("Doe", result.getPersonSortByAddressByStationNumberDTOList().get(0).getLastName());
        assertEquals("123 test", result.getPersonSortByAddressByStationNumberDTOList().get(0).getAddress());
        assertEquals("Jane", result.getPersonSortByAddressByStationNumberDTOList().get(1).getFirstName());
        assertEquals("Doe", result.getPersonSortByAddressByStationNumberDTOList().get(1).getLastName());
        assertEquals("123 test", result.getPersonSortByAddressByStationNumberDTOList().get(1).getAddress());
        assertEquals("Joe", result.getPersonSortByAddressByStationNumberDTOList().get(2).getFirstName());
        assertEquals("Smith", result.getPersonSortByAddressByStationNumberDTOList().get(2).getLastName());
        assertEquals("543 test", result.getPersonSortByAddressByStationNumberDTOList().get(2).getAddress());
        assertEquals(6, result.getPersonSortByAddressByStationNumberDTOList().get(2).getAge());

    }

    @Test
    void personByAddressWithMedicalRecord() {

        String address = "123 test";
        FireStation fireStation1 = new FireStation("3", "123 test");
        Person person1 = new Person("John", "Doe", "123 test", "city", 12345, "032323", "email@test.com");
        MedicalRecord medicalRecord1 = new MedicalRecord("John", "Doe", "03/03/1989");

        when(personRepository.findAll()).thenReturn(List.of(person1));
        when(fireStationRepository.findAll()).thenReturn(List.of(fireStation1));
        when(medicalRecordRepository.findAll()).thenReturn(List.of(medicalRecord1));

        ListPersonFireByAddressDTO result = globalService.personByAddressWithMedicalRecord(address);

        assertNotNull(result);

        assertEquals("Doe", result.getPersonFireAddressMedicalRecordDTOList().getFirst().getLastName());
        assertEquals(35, result.getPersonFireAddressMedicalRecordDTOList().getFirst().getAge());
        assertEquals("032323", result.getPersonFireAddressMedicalRecordDTOList().getFirst().getPhone());
    }

    @Test
    void personInfoEachHabitantTest() {

        String lastName = "Doe";

        Person person1 = new Person("John", "Doe", "123 test", "city", 12345, "032323", "email@test.com");
        MedicalRecord medicalRecord1 = new MedicalRecord("John", "Doe", "03/03/1989");
        when(personRepository.findAll()).thenReturn(List.of(person1));
        when(medicalRecordRepository.findAll()).thenReturn(List.of(medicalRecord1));

        ListPersonInfoByLastNameDTO result = globalService.personInfoEachHabitant(lastName);

        assertNotNull(result);

        assertEquals("Doe", result.getPersonNameByLastNameDTOList().getFirst().getLastName());
        assertEquals(35, result.getPersonNameByLastNameDTOList().getFirst().getAge());
        assertEquals("email@test.com", result.getPersonNameByLastNameDTOList().getFirst().getEmail());

    }

}
