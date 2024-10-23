package com.SafetyNet.Projet5.service;

import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getByName(final String name)  {
        return personRepository.findByName(name).orElse(null);
    }

    public List<Person> getAll() throws IOException {
        return personRepository.findAll();
    }

    public void deleteByName(final String name) {
        personRepository.deleteByName(name);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

}
