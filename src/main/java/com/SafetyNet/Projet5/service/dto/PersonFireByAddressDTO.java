package com.SafetyNet.Projet5.service.dto;

import java.util.List;

public class PersonFireByAddressDTO {

    private String lastName;
    private String phone;
    private List<String> medications;
    private List<String> allergies;
    private int age;


    public PersonFireByAddressDTO() {
    }

    public PersonFireByAddressDTO(String lastName, String phone, List<String> medications, List<String> allergies, int age) {
        this.lastName = lastName;
        this.phone = phone;
        this.medications = medications;
        this.allergies = allergies;
        this.age= age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "PersonFireAddressMedicalRecord{" +
                "lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", medications=" + medications +
                ", allergies=" + allergies +
                ", age=" + age +
                '}';
    }
}
