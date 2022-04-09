package com.abernathy.assess.controller;

import com.abernathy.assess.model.dto.AssessDTO;
import com.abernathy.assess.service.AssessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class AssessControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private MockMvc mockMvc;
    private AssessService assessService;
    private AssessController assessController;

    @BeforeEach
    public void setup() {
        assessService = Mockito.mock(AssessService.class);
        assessController = new AssessController(assessService);
        mockMvc = MockMvcBuilders.standaloneSetup(assessController).build();
    }

    @Test
    void getAssess() throws Exception {
        AssessDTO assessDTO = AssessDTO.builder().old(70).patientName("patientName").status("status").build();
        Mockito.when(assessService.get(1L)).thenReturn(assessDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/assess/1", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("status"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientName").value("patientName"))
        ;
    }
}
