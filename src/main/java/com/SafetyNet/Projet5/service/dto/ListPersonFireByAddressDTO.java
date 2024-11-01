package com.SafetyNet.Projet5.service.dto;

import java.util.List;

public class ListPersonFireByAddressDTO {

    private List<PersonFireByAddressDTO> personFireByAddressDTOList;
    private String station;

    public ListPersonFireByAddressDTO() {
    }

    public ListPersonFireByAddressDTO(List<PersonFireByAddressDTO> personFireByAddressDTOList, String station) {
        this.personFireByAddressDTOList = personFireByAddressDTOList;
    }

    public List<PersonFireByAddressDTO> getPersonFireAddressMedicalRecordDTOList() {
        return personFireByAddressDTOList;
    }

    public void setPersonFireAddressMedicalRecordDTOList(List<PersonFireByAddressDTO> personFireByAddressDTOList) {
        this.personFireByAddressDTOList = personFireByAddressDTOList;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "ListPersonFireAddressMedicalRecordDTO{" +
                "personFireAddressMedicalRecordDTOList=" + personFireByAddressDTOList +
                ", station='" + station + '\'' +
                '}';
    }
}
