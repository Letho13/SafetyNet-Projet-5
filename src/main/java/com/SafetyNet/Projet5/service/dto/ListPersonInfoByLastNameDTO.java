package com.SafetyNet.Projet5.service.dto;

import java.util.List;

public class ListPersonInfoByLastNameDTO {

    private List<PersonInfoByLastNameDTO> personNameByLastNameDTOList;

    public ListPersonInfoByLastNameDTO() {
    }

    public ListPersonInfoByLastNameDTO(List<PersonInfoByLastNameDTO> personNameByLastNameDTOList) {
        this.personNameByLastNameDTOList = personNameByLastNameDTOList;
    }

    public List<PersonInfoByLastNameDTO> getPersonNameByLastNameDTOList() {
        return personNameByLastNameDTOList;
    }

    public void setPersonNameByLastNameDTOList(List<PersonInfoByLastNameDTO> personNameByLastNameDTOList) {
        this.personNameByLastNameDTOList = personNameByLastNameDTOList;
    }

    @Override
    public String toString() {
        return "ListPersonInfoByLastNameDTO{" +
                "personNameByLastNameDTOList=" + personNameByLastNameDTOList +
                '}';
    }
}
