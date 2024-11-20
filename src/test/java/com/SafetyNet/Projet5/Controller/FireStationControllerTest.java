package com.SafetyNet.Projet5.Controller;

import com.SafetyNet.Projet5.controller.FireStationController;
import com.SafetyNet.Projet5.controller.PersonController;
import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.model.Person;
import com.SafetyNet.Projet5.repository.FireStationRepository;
import com.SafetyNet.Projet5.repository.PersonRepository;
import com.SafetyNet.Projet5.service.FireStationService;
import com.SafetyNet.Projet5.service.PersonService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = FireStationController.class)

public class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @MockBean
    private FireStationRepository fireStationRepository;

    @Test
    public void testGetAllStation() throws Exception {
        List<FireStation> stations = new ArrayList<>();
        stations.add(new FireStation("1", "test address 1"));
        stations.add(new FireStation("2", "test address 2"));
        when(fireStationService.getAll()).thenReturn(stations);
        mockMvc.perform(get("/fireStation")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].station").value("1"))
                .andExpect(jsonPath("$[0].address").value("test address 1"))
                .andExpect(jsonPath("$[1].station").value("2"))
                .andExpect(jsonPath("$[1].address").value("test address 2"));
    }

    @Test
    public void testGetAFireStationByAddress() throws Exception {
        FireStation fireStation = new FireStation("2", "test");
        when(fireStationService.getByAddress("test")).thenReturn(fireStation);
        mockMvc.perform(get("/fireStation/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("2"))
                .andExpect(jsonPath("$.address").value("test"));
    }

    @Test
    public void testDeleteFireStationByAddressSuccess() throws Exception {
        mockMvc.perform(delete("/fireStation/addressTest"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteFireStationByAddressFail() throws Exception {
        doThrow(new IllegalArgumentException("FireStation not found")).when(fireStationService).deleteByAddress("NonExistentAddress");
        mockMvc.perform(delete("/fireStation/NonExistentAddress"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveFireStation() throws Exception {

        FireStation fireStation = new FireStation("2", "test address");
        when(fireStationRepository.save(any(FireStation.class))).thenReturn(fireStation);
        when(fireStationService.save(any(FireStation.class))).thenReturn(fireStation);

        mockMvc.perform(post("/fireStation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"station\": \"2\", \"address\": \"test address\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.station").value("2"))
                .andExpect(jsonPath("$.address").value("test address"));
    }

    @Disabled
    @Test
    public void testUpdateFireStationFound() throws Exception {
        FireStation fireStation = new FireStation("2", "NewAddress Test");
        when(fireStationService.update(eq("NewAddress Test"), refEq("2"))).thenReturn(fireStation);

        mockMvc.perform(put("/fireStation/NewAddress Test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"station\": \"2\", \"address\": \"NewAddress Test\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("2"))
                .andExpect(jsonPath("$.address").value("NewAddress Test"));

        verify(fireStationService, times(1)).update(eq("NewAddress Test"), refEq("2"));
    }

    @Test
    public void testUpdateFireStationNotFound() throws Exception {

        when(fireStationService.update(Mockito.eq("NonExistentAddress"), Mockito.any(String.class)))
                .thenThrow(new IllegalArgumentException("FireStation not found"));

        mockMvc.perform(put("/fireStation/NonExistentAddress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"address\": \"NonExistentAddress\" }"))
                .andExpect(status().isNotFound());
    }


}
