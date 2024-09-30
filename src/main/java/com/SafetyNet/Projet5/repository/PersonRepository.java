package com.SafetyNet.Projet5.repository;


import com.SafetyNet.Projet5.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
