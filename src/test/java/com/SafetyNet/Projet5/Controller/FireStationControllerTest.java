package com.SafetyNet.Projet5.Controller;

import com.SafetyNet.Projet5.controller.FireStationController;
import com.SafetyNet.Projet5.model.FireStation;
import com.SafetyNet.Projet5.service.FireStationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testGetAllStation() throws Exception {
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
    void testGetAFireStationByAddress() throws Exception {
        FireStation fireStation = new FireStation("2", "test");
        when(fireStationService.getByAddress("test")).thenReturn(fireStation);
        mockMvc.perform(get("/fireStation/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("2"))
                .andExpect(jsonPath("$.address").value("test"));
    }

    @Test
    void testDeleteFireStationByAddressSuccess() throws Exception {
        mockMvc.perform(delete("/fireStation/addressTest"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteFireStationByAddressFail() throws Exception {
        doThrow(new IllegalArgumentException("FireStation not found")).when(fireStationService).deleteByAddress("NonExistentAddress");
        mockMvc.perform(delete("/fireStation/NonExistentAddress"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveFireStation() throws Exception {

        FireStation fireStation = new FireStation("2", "test address");
        when(fireStationService.save(any(FireStation.class))).thenReturn(fireStation);

        mockMvc.perform(post("/fireStation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"station\": \"2\", \"address\": \"test address\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.station").value("2"))
                .andExpect(jsonPath("$.address").value("test address"));
    }


    @Test
    void testUpdateFireStationFound() throws Exception {
        FireStation fireStation = new FireStation("2", "NewAddress");
        when(fireStationService.update(eq("NewAddress Test"), refEq(fireStation))).thenReturn(fireStation);

        mockMvc.perform(put("/fireStation/NewAddressTest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fireStation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("NewAddress"))
                .andExpect(jsonPath("$.station").value("2"));
    }

    @Test
    void testUpdateFireStationNotFound() throws Exception {

        when(fireStationService.update(Mockito.eq("NonExistentAddress"), any(FireStation.class)))
                .thenThrow(new IllegalArgumentException("FireStation not found"));

        mockMvc.perform(put("/fireStation/NonExistentAddress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"address\": \"NonExistentAddress\" }"))
                .andExpect(status().isNotFound());
    }


}
