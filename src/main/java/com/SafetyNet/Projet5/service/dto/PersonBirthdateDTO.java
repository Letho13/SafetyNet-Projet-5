package com.SafetyNet.Projet5.service.dto;

public class PersonBirthdateDTO {

    private String birthdate;
    private String medicalRecordName;


    public PersonBirthdateDTO() {
    }

    public PersonBirthdateDTO(String birthdate, String medicalRecordName) {
        this.birthdate = birthdate;
        this.medicalRecordName = medicalRecordName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getMedicalRecordName() {
        return medicalRecordName;
    }

    public void setMedicalRecordName(String medicalRecordName) {
        this.medicalRecordName = medicalRecordName;
    }

    @Override
    public String toString() {
        return "PersonBirthdateDTO{" +
                "birthdate='" + birthdate + '\'' +
                ", medicalRecordName='" + medicalRecordName + '\'' +
                '}';
    }
}
