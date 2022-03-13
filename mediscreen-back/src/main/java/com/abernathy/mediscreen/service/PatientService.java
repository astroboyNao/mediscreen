package com.abernathy.mediscreen.service;

import com.abernathy.mediscreen.model.dto.PatientDTO;
import com.abernathy.mediscreen.model.mapper.PatientMapper;
import com.abernathy.mediscreen.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {
    PatientRepository patientRepository;
    PatientMapper patientMapper;

    public List<PatientDTO> getAll() {
        return patientMapper.toPatientDTOs(patientRepository.findAll());
    }
}
