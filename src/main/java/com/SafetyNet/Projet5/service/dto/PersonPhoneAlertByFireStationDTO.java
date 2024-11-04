package com.SafetyNet.Projet5.service.dto;

public class PersonPhoneAlertByFireStationDTO {

    private String phone;

    public PersonPhoneAlertByFireStationDTO() {
    }

    public PersonPhoneAlertByFireStationDTO(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PhoneNumberFireStation{" +
                "phone='" + phone + '\'' +
                '}';
    }
}
