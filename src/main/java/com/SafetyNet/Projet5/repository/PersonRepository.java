package com.SafetyNet.Projet5.repository;


import com.SafetyNet.Projet5.model.DataJson;
import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.util.DataReaderUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    DataReaderUtil dataReaderUtil = new DataReaderUtil();
    private List<Person> persons = new ArrayList<>();

    public PersonRepository(List<Person> persons) throws IOException {
        this.persons = dataReaderUtil.getPersons();
    }

    public Optional<Person> findByEmail(String email){
        return persons.stream()
                .filter(person -> person.getEmail().equals(email))
                .findFirst();
    }

    public List<Person> findAll() throws IOException {
        return persons;
    }

    public void deleteByEmail(String email){

    }

    public Person save(Person person){
        return null;
    }
}
