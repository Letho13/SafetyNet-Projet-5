package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final static Logger LOG = LoggerFactory.getLogger(PersonController.class);
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAll() throws IOException {
        List<Person> persons = personService.getAll();
        LOG.info("Successfully retrieved {} Persons.", persons.size());
        return persons;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Person> getByName(@PathVariable String name) {
        try {
            Person person = personService.getByName(name);
            LOG.info("Successfully retrieved Person with name: {}", name);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            LOG.error("Error retrieving Person with name {}: {}", name, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name) {
        try {
            personService.deleteByName(name);
            LOG.info("Successfully deleted Person with name: {}", name);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            LOG.error("Error deleting Person with name {}: {}", name, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        LOG.info("Successfully saved Person with Name: {}", savedPerson.getFullName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Person> updatePerson(@PathVariable String name, @RequestBody Person updatedPerson) {
        try {
            Person person = personService.update(name, updatedPerson);
            LOG.info("Updated person: {}", person);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            LOG.error("Error updating Person with name {}: {}", name, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
