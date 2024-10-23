package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.PersonRepository;
import com.SafetyNet.Projet5.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;


    public PersonController(PersonService personService) {
        this.personService = personService;

    }

    @GetMapping
    public List<Person> getAll() throws IOException {
        return personService.getAll();
    }

    @GetMapping("{name}")
    public Person getByName(@PathVariable String name) {
        return personService.getByName(name);
    }

    @DeleteMapping("{name}")
    public void deleteByName(@PathVariable String name) {
        personService.deleteByName(name);
    }

    @PostMapping
    public Person save(@RequestBody Person person){
        return personService.save(person);
    }

}
