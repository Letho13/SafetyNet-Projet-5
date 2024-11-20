package com.SafetyNet.Projet5.Controller;

import com.SafetyNet.Projet5.controller.PersonController;
import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.PersonRepository;
import com.SafetyNet.Projet5.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PersonController.class)

public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;


    @Test
    public void testGetAllPerson() throws Exception {
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", "Doe"));
        people.add(new Person("Jane", "Doe"));
        when(personService.getAll()).thenReturn(people);
        mockMvc.perform(get("/person")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Doe"));
    }

    @Test
    public void testGetAPersonByName() throws Exception {
        Person person = new Person("John", "Doe");
        when(personService.getByName("JohnDoe")).thenReturn(person);
        mockMvc.perform(get("/person/JohnDoe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testDeletePersonByNameSuccess() throws Exception {
        mockMvc.perform(delete("/person/JohnDoe"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeletePersonByNameFail() throws Exception {
        doThrow(new IllegalArgumentException("Person not found")).when(personService).deleteByName("NonExistentName");
        mockMvc.perform(delete("/person/NonExistentName"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSavePerson() throws Exception {

        Person person = new Person("John", "Doe");
        when(personRepository.save(any(Person.class))).thenReturn(person);
        when(personService.save(any(Person.class))).thenReturn(person);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }



    @Test
    public void testUpdatePersonFound() throws Exception {
        Person person = new Person("John", "Doe", "New Address", "New City", 12345, "3499329432", "newemail@example.com");
        when(personService.update(eq("JohnDoe"), any(Person.class))).thenReturn(person);

        mockMvc.perform(put("/person/JohnDoe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"address\": \"New Address\", \"city\": \"New City\", \"zip\": 12345, \"phone\": \"3499329432\", \"email\": \"newemail@example.com\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("New Address"))
                .andExpect(jsonPath("$.city").value("New City"))
                .andExpect(jsonPath("$.phone").value("3499329432"))
                .andExpect(jsonPath("$.email").value("newemail@example.com"));

        verify(personService, times(1)).update(eq("JohnDoe"), refEq(person));
    }

    @Test
    public void testUpdatePersonNotFound() throws Exception {

        when(personService.update(Mockito.eq("NonExistentName"), Mockito.any(Person.class)))
                .thenThrow(new IllegalArgumentException("Person not found"));

        mockMvc.perform(put("/person/NonExistentName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Non\", \"lastName\": \"Existent\" }"))
                .andExpect(status().isNotFound());
    }
}



