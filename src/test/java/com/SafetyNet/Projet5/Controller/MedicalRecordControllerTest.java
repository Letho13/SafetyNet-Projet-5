package com.SafetyNet.Projet5.Controller;

import com.SafetyNet.Projet5.controller.MedicalRecordController;
import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = MedicalRecordController.class)

public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;


    @Test
    public void testGetAllMedicalRecord() throws Exception {

        List<MedicalRecord> records = List.of(
                new MedicalRecord("John", "Doe", "12/11/2002", List.of("Aspirine", "Doliprane : 500mg"), List.of("Chat", "Noix"))
        );

        when(medicalRecordService.getAll()).thenReturn(records);

        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].birthdate").value("12/11/2002"))
                .andExpect(jsonPath("$[0].medications.[0]").value("Aspirine"))
                .andExpect(jsonPath("$[0].medications.[1]").value("Doliprane : 500mg"))
                .andExpect(jsonPath("$[0].allergies.[0]").value("Chat"))
                .andExpect(jsonPath("$[0].allergies.[1]").value("Noix"));

    }
}
