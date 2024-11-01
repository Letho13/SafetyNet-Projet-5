package com.SafetyNet.Projet5.service.dto;

import java.util.List;

public class PersonInfoByLastNameDTO {

    private String lastName;
    private String firstName;
    private String address;
    private int age;
    private String email;
    private List<String> medications;
    private List<String> allergies;

    public PersonInfoByLastNameDTO() {
    }

    public PersonInfoByLastNameDTO(String lastName, String firstName,String address, int age, String email, List<String> medications, List<String> allergies) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.age = age;
        this.email = email;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "PersonInfoByLastNameDTO{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }
}
