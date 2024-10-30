package com.SafetyNet.Projet5.service.dto;

import com.SafetyNet.Projet5.model.Person;

import java.util.ArrayList;
import java.util.List;

public class ListChildAlertDTO {

    private List<PersonChildAlertDTO> childAlertDTOS;

    private List<Person> otherMember;

    public ListChildAlertDTO() {
        this.childAlertDTOS = new ArrayList<>();
        this.otherMember = new ArrayList<>();
    }

    public ListChildAlertDTO(List<PersonChildAlertDTO> childAlertDTOS, List<Person> otherMember) {
        this.childAlertDTOS = childAlertDTOS;
        this.otherMember = otherMember;
    }

    public List<PersonChildAlertDTO> getChildAlertDTOS() {
        return childAlertDTOS;
    }

    public void setChildAlertDTOS(List<PersonChildAlertDTO> childAlertDTOS) {
        this.childAlertDTOS = childAlertDTOS;
    }

    public List<Person> getOtherMember() {
        return otherMember;
    }

    public void setOtherMember(List<Person> otherMember) {
        this.otherMember = otherMember;
    }

    @Override
    public String toString() {
        return "ListChildAlertDTO{" +
                "childAlertDTOS=" + childAlertDTOS +
                ", otherMember=" + otherMember +
                '}';
    }


}



