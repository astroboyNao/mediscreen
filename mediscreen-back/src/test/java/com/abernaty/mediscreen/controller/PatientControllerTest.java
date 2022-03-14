package com.abernaty.mediscreen.controller;

import com.abernathy.mediscreen.controller.PatientController;
import com.abernathy.mediscreen.model.dto.PatientDTO;
import com.abernathy.mediscreen.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
    private MockMvc mockMvc;
    private PatientService patientService;
    private PatientController patientController;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @BeforeEach
    public void setup() {
        patientService = Mockito.mock(PatientService.class);
        patientController = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    void getAllPatient() throws Exception {
        List<PatientDTO> patientDTOS = new ArrayList<>();
        patientDTOS.add(
                PatientDTO.builder()
                        .name("patient")
                        .firstName("firstName")
                        .sex('M')
                        .address("address")
                        .family("family")
                        .dob(LocalDate.of(1999, 12, 31))
                        .build());
        Mockito.when(patientService.getAll()).thenReturn(patientDTOS);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("patient"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("firstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sex").value("M"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].family").value("family"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dob").value("1999-12-31"))
        ;
    }

    @Test
    void updatePatient() throws Exception {
        PatientDTO patientDTO = PatientDTO.builder()
                .name("patient")
                .firstName("firstName")
                .sex('M')
                .address("address")
                .family("family")
                .dob(LocalDate.of(1999, 12, 31))
                .build();
        Mockito.when(patientService.editPatient(Mockito.any(PatientDTO.class))).thenReturn(patientDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"patient\", \"firstName\":\"firstName\", \"sex\":\"M\"," +
                                "\"address\": \"address\",\"family\": \"family\",\"dob\":\"1999-12-01\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("patient"))
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").value("firstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("sex").value("M"))
                .andExpect(MockMvcResultMatchers.jsonPath("address").value("address"))
                .andExpect(MockMvcResultMatchers.jsonPath("family").value("family"))
                .andExpect(MockMvcResultMatchers.jsonPath("dob").value("1999-12-31"))
        ;
    }
}
