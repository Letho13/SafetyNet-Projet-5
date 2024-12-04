package com.SafetyNet.Projet5.Controller;

import com.SafetyNet.Projet5.controller.GlobalController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GlobalController.class)
public class GlobalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GlobalController globalController;

    @Test
    void personsFireStationByStationNumberTest () throws Exception{
        mockMvc.perform(get("/firestation/3")).andExpect(status().isOk());
    }

    @Test
    void childAlertByAddressTest () throws Exception{
        mockMvc.perform(get("/childAlert/address")).andExpect(status().isOk());
    }

    @Test
    void phoneNumberByFireStationStationNumberTest () throws Exception{
        mockMvc.perform(get("/phoneAlert/2")).andExpect(status().isOk());
    }

    @Test
    void personByAddressWithMedicalRecordTest () throws Exception{
        mockMvc.perform(get("/fire/address")).andExpect(status().isOk());
    }

    @Test
    void personByStationNumberSortedByAddressTest () throws Exception{
        mockMvc.perform(get("/flood/stations?stations=1,2,3")).andExpect(status().isOk());
    }

    @Test
    void personInfoEachHabitantTest () throws Exception{
        mockMvc.perform(get("/personInfolastName/Name")).andExpect(status().isOk());
    }

    @Test
    void PersonEmailByCityTest () throws Exception{
        mockMvc.perform(get("/CommunityEmail/city?city=Culver")).andExpect(status().isOk());
    }

}
