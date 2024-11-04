package com.SafetyNet.Projet5.service.dto;

import java.util.List;

public class ListPersonPhoneAlertByFireStationDTO {

    private List<PersonPhoneAlertByFireStationDTO> personPhoneAlertByFireStationDTOList;

    public ListPersonPhoneAlertByFireStationDTO() {
    }

    public ListPersonPhoneAlertByFireStationDTO(List<PersonPhoneAlertByFireStationDTO> personPhoneAlertByFireStationDTOList) {
        this.personPhoneAlertByFireStationDTOList = personPhoneAlertByFireStationDTOList;
    }

    public List<PersonPhoneAlertByFireStationDTO> getPhoneNumberFireStationDTOList() {
        return personPhoneAlertByFireStationDTOList;
    }

    public void setPhoneNumberFireStationDTOList(List<PersonPhoneAlertByFireStationDTO> personPhoneAlertByFireStationDTOList) {
        this.personPhoneAlertByFireStationDTOList = personPhoneAlertByFireStationDTOList;
    }

    @Override
    public String toString() {
        return "ListPhoneNumberFireStationDTO{" +
                "phoneNumberFireStationDTOList=" + personPhoneAlertByFireStationDTOList +
                '}';
    }
}
