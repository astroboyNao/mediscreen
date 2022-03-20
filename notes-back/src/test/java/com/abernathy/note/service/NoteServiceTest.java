package com.abernathy.note.service;

import com.abernathy.notes.model.domain.Note;
import com.abernathy.notes.model.dto.NoteDTO;
import com.abernathy.notes.model.mapper.NoteMapper;
import com.abernathy.notes.repository.NoteRepository;
import com.abernathy.notes.service.NoteService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class NoteServiceTest {
    NoteRepository noteRepository;
    NoteMapper noteMapper;
    NoteService noteService;

    @BeforeEach
    void init() {
        noteRepository = Mockito.mock(NoteRepository.class);
        noteMapper = Mockito.mock(NoteMapper.class);
        noteService = new NoteService(noteRepository, noteMapper);
    }

    @Test
    void shouldGetNotesForPatient() {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNote("note");
        noteDTO.setId("idNote");
        noteDTO.setRiskLevel("riskLevel");

        Note note = new Note();
        note.setNote("note");
        note.setId("idNote");
        note.setRiskLevel("riskLevel");
        Mockito.when(noteRepository.findByPatientId(1L)).thenReturn(List.of(note));
        Mockito.when(noteMapper.toNoteDTOs(Mockito.anyList())).thenReturn(List.of(noteDTO));

        var notesResult = noteService.getAllForPatientId(1L);

        Assertions.assertThat(notesResult.size()).isEqualTo(1);
        Assertions.assertThat(notesResult.get(0).getNote()).isEqualTo("note");

    }

    @Test
    void addNotesForNote() {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNote("note");
        noteDTO.setId("idNote");
        noteDTO.setRiskLevel("riskLevel");

        Note note = new Note();
        note.setNote("note");
        note.setId("idNote");
        note.setPatientId(1L);
        note.setRiskLevel("riskLevel");
        Note[] notes = {note};

        Mockito.when(noteRepository.save(note)).thenReturn(note);
        Mockito.when(noteMapper.toNoteDTO(note)).thenReturn(noteDTO);
        Mockito.when(noteMapper.toNote(noteDTO)).thenReturn(note);

        var noteResult = noteService.addNoteForPatientId(noteDTO, 1L);

        Assertions.assertThat(noteResult.getNote()).isEqualTo("note");

    }
}
