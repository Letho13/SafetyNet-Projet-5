package com.SafetyNet.Projet5.service;

import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final static Logger LOG = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getByName(final String name) {
        LOG.debug("Attempting to retrieve Person by name: {}", name);
        Optional<Person> byName = personRepository.findByName(name);

        if (byName.isEmpty()) {
            LOG.error("Person not found for name: {}", name);
            throw new IllegalArgumentException("Person not found");
        }

        LOG.info("Successfully retrieved Person by name: {}", name);
        return byName.get();
    }

    public List<Person> getAll() throws IOException {
        LOG.debug("Fetching all persons from the repository.");
        List<Person> persons = personRepository.findAll();

        LOG.info("Successfully retrieved {} persons.", persons.size());
        return persons;
    }

    public void deleteByName(final String name) {
        LOG.debug("Attempting to delete Person by name: {}", name);

        if (name.isEmpty()) {
            LOG.error("Delete failed: name is empty.");
            throw new IllegalArgumentException("Name must not be empty");
        }

        personRepository.deleteByName(name);
        LOG.info("Successfully deleted Person by name: {}", name);
    }

    public Person update(final String name, final Person updatedPerson) {
        LOG.debug("Attempting to update Person with name: {}", name);

        Person existingPerson = personRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Person not found"));

        Person personToSave = getPerson(updatedPerson, Optional.ofNullable(existingPerson));
        Person savedPerson = personRepository.updatePerson(name,personToSave);

        LOG.info("Successfully updated Person: {}", savedPerson);
        return savedPerson;
    }

    private static Person getPerson(Person updatedPerson, Optional<Person> existingPerson) {
        LOG.debug("Updating fields for Person based on provided updated information.");
        Person personToSave = existingPerson.get();
        personToSave.setFirstName(updatedPerson.getFirstName());
        personToSave.setLastName(updatedPerson.getLastName());
        personToSave.setAddress(updatedPerson.getAddress());
        personToSave.setCity(updatedPerson.getCity());
        personToSave.setZip(updatedPerson.getZip());
        personToSave.setPhone(updatedPerson.getPhone());
        personToSave.setEmail(updatedPerson.getEmail());


        LOG.debug("Person fields successfully updated.");
        return personToSave;
    }

    public Person save(Person person) {
        LOG.debug("Attempting to save a new Person: {}", person);
        Person savedPerson = personRepository.save(person);

        LOG.info("Successfully saved Person: {}", savedPerson);
        return savedPerson;
    }
}
