package com.abernaty.mediscreen.repository;


import com.abernathy.mediscreen.controller.PatientController;
import com.abernathy.mediscreen.model.domain.Note;
import com.abernathy.mediscreen.repository.NoteRepository;
import com.abernathy.mediscreen.repository.NoteRepositoryImpl;
import com.abernathy.mediscreen.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

public class NoteRepositoryImplTest {
    @Mock
    private RestTemplate restTemplate;
    private String urlBaseApiNotesTest = "urlBaseApiNotesTest";
    private NoteRepository noteRepository;

    @BeforeEach
    public void setup() {
        restTemplate = Mockito.mock(RestTemplate.class);
        noteRepository = new NoteRepositoryImpl(restTemplate, urlBaseApiNotesTest);
    }

    @Test
    public void getAllNoteForPatient() {
        Note note = new Note();
        note.setNote("note");
        Note[] notes = {note};
        Mockito.when(restTemplate.getForObject("urlBaseApiNotesTest/notes/1", Note[].class))
                .thenReturn(notes);
        Note[] noteResult = noteRepository.getAllForPatient(1L);
        Assertions.assertEquals(noteResult.length, 1);
    }
}