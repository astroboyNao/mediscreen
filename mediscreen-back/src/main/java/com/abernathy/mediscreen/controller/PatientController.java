package com.abernathy.mediscreen.controller;

import com.abernathy.mediscreen.model.domain.Patient;
import com.abernathy.mediscreen.model.dto.PatientDTO;
import com.abernathy.mediscreen.service.PatientService;
import lombok.AllArgsConstructor;
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
}
