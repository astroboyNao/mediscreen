package com.abernathy.note.controller;

import com.abernathy.notes.controller.NoteController;
import com.abernathy.notes.model.dto.NoteDTO;
import com.abernathy.notes.service.NoteService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class NoteControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private MockMvc mockMvc;
    private NoteService noteService;
    private NoteController noteController;

    @BeforeEach
    public void setup() {
        noteService = Mockito.mock(NoteService.class);
        noteController = new NoteController(noteService);
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    void getAllNote() throws Exception {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNote("note");
        noteDTO.setId("id");
        noteDTO.setRiskLevel("riskLevel");
        Mockito.when(noteService.getAllForPatientId(1L)).thenReturn(List.of(noteDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/notes/1", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].note").value("note"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].riskLevel").value("riskLevel"))
        ;
    }

    @Test
    void addNote() throws Exception {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNote("note");
        noteDTO.setId("id");
        noteDTO.setRiskLevel("riskLevel");
        Mockito.when(noteService.addNoteForPatientId(Mockito.any(NoteDTO.class), Mockito.anyLong())).thenReturn(noteDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/note/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"note\": \"note\", \"riskLevel\":\"riskLevel\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("note").value("note"))
                .andExpect(MockMvcResultMatchers.jsonPath("riskLevel").value("riskLevel"))
        ;
    }

}
