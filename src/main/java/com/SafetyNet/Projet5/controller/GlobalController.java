package com.SafetyNet.Projet5.controller;

import com.SafetyNet.Projet5.service.GlobalService;
import com.SafetyNet.Projet5.service.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GlobalController {

    private final GlobalService globalService;

    public GlobalController(GlobalService globalService) {
        this.globalService = globalService;
    }

    @GetMapping("/firestation/{stationNumber}")
    public ListPersonFireStationDTO personsFireStationByStationNumber (@PathVariable String stationNumber) {
        return globalService.personsFireStationByStationNumber(stationNumber);
    }

    @GetMapping( "/childAlert/{address}")
     public ListChildAlertDTO childAlertByAddress (@PathVariable String address) {
        return globalService.childAlertByAddress(address);
    }

    @GetMapping( "/phoneAlert/{stationNumber}")
    public ListPersonPhoneAlertByFireStationDTO phoneNumberByFireStationStationNumber (@PathVariable String stationNumber) {
        return globalService.phoneNumberByFireStationStationNumber(stationNumber);
    }

    @GetMapping( "/fire/{address}")
    public ListPersonFireByAddressDTO personByAddressWithMedicalRecord (@PathVariable String address) {
        return globalService.personByAddressWithMedicalRecord(address);
    }

    @GetMapping("/flood/stations")
    public ListPersonFloodStationSortByAddressDTO personByStationNumberSortedByAddress (@RequestParam List<String> stations){
        return globalService.personByStationNumberSortedByAddress(stations);
    }

    @GetMapping( "/personInfolastName/{lastName}")
    public ListPersonInfoByLastNameDTO personInfoEachHabitant (@PathVariable String lastName) {
        return globalService.personInfoEachHabitant(lastName);
    }

    @GetMapping( "/CommunityEmail/city")
    public List<String> PersonEmailByCity (@RequestParam String city) {
        return globalService.PersonEmailByCity(city);
    }

}
