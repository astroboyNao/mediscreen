package com.abernathy.mediscreen.controller;

import com.abernathy.mediscreen.model.domain.Patient;
import com.abernathy.mediscreen.model.dto.PatientDTO;
import com.abernathy.mediscreen.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PatientController {

    PatientService patientService;

    @GetMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PatientDTO> getAllPatient() {
        return patientService.getAll();
    }
}
