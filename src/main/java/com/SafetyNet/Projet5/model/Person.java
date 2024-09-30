package com.SafetyNet.Projet5.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "persons")


public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String firstname;

    private String lastname;

    private String address;

    private String city;

    private int zip;


}
