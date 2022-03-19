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

}
