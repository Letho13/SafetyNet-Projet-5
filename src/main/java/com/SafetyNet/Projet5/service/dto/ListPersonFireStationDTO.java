package com.SafetyNet.Projet5.service.dto;

import java.util.List;

public class ListPersonFireStationDTO {

    private List<PersonFireStationDTO> personFireStationDTOList;
    private int nombreAdultes;
    private int nombreEnfants;

    public ListPersonFireStationDTO() {
    }

    public ListPersonFireStationDTO(List<PersonFireStationDTO> personFireStationDTOList, int nombreAdultes, int nombreEnfants) {
        this.personFireStationDTOList = personFireStationDTOList;
        this.nombreAdultes = nombreAdultes;
        this.nombreEnfants = nombreEnfants;
    }

    public List<PersonFireStationDTO> getPersonFireStationDTOList() {
        return personFireStationDTOList;
    }

    public void setPersonFireStationDTOList(List<PersonFireStationDTO> personFireStationDTOList) {
        this.personFireStationDTOList = personFireStationDTOList;
    }

    public int getNombreAdultes() {
        return nombreAdultes;
    }

    public void setNombreAdultes(int nombreAdultes) {
        this.nombreAdultes = nombreAdultes;
    }

    public int getNombreEnfants() {
        return nombreEnfants;
    }

    public void setNombreEnfants(int nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }

    @Override
    public String toString() {
        return "ListPersonFireStationDTO{" +
                "personFireStationDTOList=" + personFireStationDTOList +
                ", nombreAdultes=" + nombreAdultes +
                ", nombreEnfants=" + nombreEnfants +
                '}';
    }
}
