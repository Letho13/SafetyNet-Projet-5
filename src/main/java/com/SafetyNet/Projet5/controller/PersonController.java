package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public Iterable<Person> getPerson() throws IOException {
        return personService.getPerson();
    }

}
