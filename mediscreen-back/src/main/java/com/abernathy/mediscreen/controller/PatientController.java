package com.abernathy.mediscreen.controller;

import com.abernathy.mediscreen.model.domain.Patient;
import com.abernathy.mediscreen.model.dto.NoteDTO;
import com.abernathy.mediscreen.model.dto.PatientDTO;
import com.abernathy.mediscreen.service.PatientService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PatientController {

    PatientService patientService;

    @GetMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PatientDTO> getAllPatient() {
        return patientService.getAll();
    }

    @PutMapping("/patient")
    public PatientDTO editPatient(@RequestBody PatientDTO patient) {
        return patientService.editPatient(patient);
    }

    @PostMapping("/patient")
    public PatientDTO addPatient(@RequestBody PatientDTO patient) {
        return patientService.addPatient(patient);
    }

    @GetMapping(value = "/patient/{patientId}/notes")
    public List<NoteDTO> getAllNoteForPatient(@PathVariable("patientId") Long patientId) {
        return patientService.getAllNotes(patientId);
    }

    @PostMapping(value = "/patient/{patientId}/note")
    public NoteDTO addNoteForPatient(@PathVariable("patientId") Long patientId, @RequestBody NoteDTO noteDTO) {
        return patientService.addNoteForPatient(noteDTO, patientId);
    }

    @PutMapping(value = "/patient/{patientId}/note/{noteId}")
    public NoteDTO updateNoteForPatient(@PathVariable("patientId") Long patientId, @PathVariable("noteId") String noteId, @RequestBody NoteDTO noteDTO) {
        noteDTO.setId(noteId);
        return patientService.updateNoteForPatient(noteDTO, patientId);
    }
}
