package com.SafetyNet.Projet5.service;

import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(final String email) {
        return personRepository.findByEmail(email);
    }

    public List<Person> getPerson() throws IOException {
        return personRepository.findAll();
    }

    public void deletePerson(final String email) {
        personRepository.deleteByEmail(email);
    }

    public Person savePerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }

}
