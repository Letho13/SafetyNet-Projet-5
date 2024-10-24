package com.SafetyNet.Projet5.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private List <String> medications;
    private List <String> allergies;
    private String medicalRecordName;

    public MedicalRecord() {
    }

    public MedicalRecord(String firstName, String lastName, String birthDate, List <String> medications, List <String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthDate;
        this.medications = medications;
        this.allergies = allergies;
        this.medicalRecordName = firstName + lastName;
    }

    public String getMedicalRecordName() {
        return medicalRecordName;
    }

    public void setMedicalRecordName(String medicalRecordName) {
        this.medicalRecordName = medicalRecordName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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
        return "MedicalRecord{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", medications=" + medications +
                ", allergies=" + allergies +
                ", medicalRecordName='" + medicalRecordName + '\'' +
                '}';
    }

}
