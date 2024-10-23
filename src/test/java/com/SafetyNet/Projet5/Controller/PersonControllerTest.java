package com.SafetyNet.Projet5.Controller;

import com.SafetyNet.Projet5.controller.PersonController;
import com.SafetyNet.Projet5.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private Mockmvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    public void testGetPerson() throws Exception{

       mockMvc.perform(get("/persons")).andExpect(status().isOk());

    }
}
