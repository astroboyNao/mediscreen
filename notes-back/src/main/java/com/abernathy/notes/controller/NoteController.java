package com.abernathy.notes.controller;

import com.abernathy.notes.model.dto.NoteDTO;
import com.abernathy.notes.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class NoteController {

    NoteService noteService;

    @GetMapping(value = "/notes/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllPatient(@PathVariable("patientId") Long patientId) {
        return noteService.getAllForPatientId(patientId);
    }

    @PostMapping(value = "/note/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDTO addNoteForPatient(@PathVariable("patientId") Long patientId, @RequestBody NoteDTO note) {
        return noteService.addNoteForPatientId(note, patientId);
    }

    @PutMapping(value = "/note/{noteId}/patient/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDTO updateNoteForPatient(@PathVariable("patientId") Long patientId, @PathVariable("noteId") String noteId, @RequestBody NoteDTO note) {
        note.setId(noteId);
        return noteService.updateNote(note, patientId);
    }
}
