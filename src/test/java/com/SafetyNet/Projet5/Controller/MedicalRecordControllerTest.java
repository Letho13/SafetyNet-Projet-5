package com.SafetyNet.Projet5.Controller;

import com.SafetyNet.Projet5.controller.MedicalRecordController;
import com.SafetyNet.Projet5.model.MedicalRecord;
import com.SafetyNet.Projet5.service.MedicalRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MedicalRecordController.class)

public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testGetAllMedicalRecord() throws Exception {

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

    @Test
    void testGetAMedicalRecordByName() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "12/11/2002", List.of("Aspirine", "Doliprane : 500mg"), List.of("Chat", "Noix")
        );
        when(medicalRecordService.getByMedicalRecordName("JohnDoe")).thenReturn(medicalRecord);
        mockMvc.perform(get("/medicalRecord/JohnDoe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.birthdate").value("12/11/2002"))
                .andExpect(jsonPath("$.medications.[0]").value("Aspirine"))
                .andExpect(jsonPath("$.medications.[1]").value("Doliprane : 500mg"))
                .andExpect(jsonPath("$.allergies.[0]").value("Chat"))
                .andExpect(jsonPath("$.allergies.[1]").value("Noix"));
    }

    @Test
    void testDeleteMedicalRecordByNameSuccess() throws Exception {
        mockMvc.perform(delete("/medicalRecord/JohnDoe"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteMedicalRecordByNameFail() throws Exception {
        doThrow(new IllegalArgumentException("MedicalRecord not found")).when(medicalRecordService).deleteByName("NonExistentName");
        mockMvc.perform(delete("/medicalRecord/NonExistentName"))
                .andExpect(status().isNotFound());
    }


    @Test
    void testSaveMedicalRecord() throws Exception {


        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "10/09/2009");
        when(medicalRecordService.save(any(MedicalRecord.class))).thenReturn(medicalRecord);

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.birthdate").value("10/09/2009"));
    }


    @Test
    void testUpdateMedicailRecordFound() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "10/09/2009");
        when(medicalRecordService.update(eq("JohnDoe"), refEq(medicalRecord))).thenReturn(medicalRecord);

        mockMvc.perform(put("/medicalRecord/JohnDoe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthdate").value("10/09/2009"));

        verify(medicalRecordService, times(1)).update(eq("JohnDoe"), refEq(medicalRecord));
    }

    @Test
    void testUpdateMedicalRecordNotFound() throws Exception {

        when(medicalRecordService.update(Mockito.eq("NonExistentName"), Mockito.any(MedicalRecord.class)))
                .thenThrow(new IllegalArgumentException("MedicalRecord not found"));

        mockMvc.perform(put("/medicalRecord/NonExistentName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Non\", \"lastName\": \"Existent\" }"))
                .andExpect(status().isNotFound());
    }

}
