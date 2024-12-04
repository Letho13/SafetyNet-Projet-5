package com.SafetyNet.Projet5.Service;

import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.PersonRepository;
import com.SafetyNet.Projet5.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByNameFound() {
        Person person = new Person("John", "Doe");
        when(personRepository.findByName("JohnDoe")).thenReturn(Optional.of(person));

        Person result = personService.getByName("JohnDoe");

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(personRepository, times(1)).findByName("JohnDoe");
    }

    @Test
    void testGetByNameNotFound() {
        when(personRepository.findByName("NonExistent")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            personService.getByName("NonExistent");
        });

        assertEquals("Person not found", exception.getMessage());
        verify(personRepository, times(1)).findByName("NonExistent");
    }

    @Test
    void testGetAllPersons() throws IOException {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Doe"));
        persons.add(new Person("Jane", "Doe"));
        when(personRepository.findAll()).thenReturn(persons);

        List<Person> result = personService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    void testSavePerson() {
        Person person = new Person("John", "Doe");
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person result = personService.save(person);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(personRepository, times(1)).save(person);
    }

    @Test
    void testDeletePersonByName() {
        doNothing().when(personRepository).deleteByName("JohnDoe");

        personService.deleteByName("JohnDoe");

        verify(personRepository, times(1)).deleteByName("JohnDoe");
    }


    @Test
    void testDeletePersonByNameThrowsException() {
        doThrow(new IllegalArgumentException("Name must not be empty"))
                .when(personRepository).deleteByName("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            personService.deleteByName("");
        });

        assertEquals("Name must not be empty", exception.getMessage());
    }


    @Test
    void testUpdatePersonFound() {
        Person existingPerson = new Person("John", "Doe");
        Person updatedPerson = new Person("John", "Doe", "New Address", "New City", 12345, "1234567890", "email@example.com");

        when(personRepository.findByName("JohnDoe")).thenReturn(Optional.of(existingPerson));
        when(personRepository.updatePerson(eq("JohnDoe"), any(Person.class))).thenReturn(updatedPerson);

        Person result = personService.update("JohnDoe", updatedPerson);

        assertNotNull(result);
        assertEquals("New Address", result.getAddress());
        assertEquals("New City", result.getCity());
        verify(personRepository, times(1)).findByName("JohnDoe");
        verify(personRepository, times(1)).updatePerson(eq("JohnDoe"), any(Person.class));
    }

    @Test
    void testUpdatePersonNotFound() {
        Person updatedPerson = new Person("Non", "Existent");

        when(personRepository.findByName("NonExistent")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            personService.update("NonExistent", updatedPerson);
        });

        assertEquals("Person not found", exception.getMessage());
        verify(personRepository, times(1)).findByName("NonExistent");
        verify(personRepository, times(0)).updatePerson(anyString(), any(Person.class));
    }
}

