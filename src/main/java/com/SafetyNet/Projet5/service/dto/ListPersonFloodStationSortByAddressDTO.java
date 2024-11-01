package com.SafetyNet.Projet5.service.dto;

import java.util.List;

public class ListPersonFloodStationSortByAddressDTO {

private List<PersonFloodStationSortByAddressDTO> personFloodStationSortByAddressDTOList;

    public ListPersonFloodStationSortByAddressDTO() {
    }

    public ListPersonFloodStationSortByAddressDTO(List<PersonFloodStationSortByAddressDTO> personFloodStationSortByAddressDTOList) {
        this.personFloodStationSortByAddressDTOList = personFloodStationSortByAddressDTOList;
    }

    public List<PersonFloodStationSortByAddressDTO> getPersonSortByAddressByStationNumberDTOList() {
        return personFloodStationSortByAddressDTOList;
    }

    public void setPersonSortByAddressByStationNumberDTOList(List<PersonFloodStationSortByAddressDTO> personFloodStationSortByAddressDTOList) {
        this.personFloodStationSortByAddressDTOList = personFloodStationSortByAddressDTOList;
    }

    @Override
    public String toString() {
        return "ListPersonSortByAddressByStationNumberDTO{" +
                "personSortByAddressByStationNumberDTOList=" + personFloodStationSortByAddressDTOList +
                '}';
    }
}
