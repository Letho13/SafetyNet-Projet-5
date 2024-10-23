package com.SafetyNet.Projet5.repository;


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

    public Optional<Person> findByName(String name){
        return persons.stream()
                .filter(person -> (person.getFirstName()+person.getLastName()).equals(name))
                .findFirst();
    }

    public List<Person> findAll()  {
        return persons;
    }

    public void deleteByName(String name){
        persons = persons.stream()
                .filter(person -> !(person.getFirstName()+person.getLastName()).equals(name))
                .toList();


    }

    public Person save(Person person){
        persons.add(person);
        return person;
    }
}
