package com.abernaty.mediscreen.service;

import com.abernathy.mediscreen.model.domain.Patient;
import com.abernathy.mediscreen.model.dto.PatientDTO;
import com.abernathy.mediscreen.model.mapper.PatientMapper;
import com.abernathy.mediscreen.repository.PatientRepository;
import com.abernathy.mediscreen.service.PatientService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class PatientServiceTest {
    PatientRepository patientRepository;
    PatientMapper patientMapper;
    PatientService patientService;

    @BeforeEach
    void init() {
        patientRepository = Mockito.mock(PatientRepository.class);
        patientMapper = Mockito.mock(PatientMapper.class);
        patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    void shouldGetAllPatients() {
        PatientDTO patient = PatientDTO.builder().name("patient").build();

        Mockito.when(patientRepository.findAll()).thenReturn(List.of(new Patient()));
        Mockito.when(patientMapper.toPatientDTOs(Mockito.anyList())).thenReturn(List.of(patient));

        var patientsResult = patientService.getAll();
        Assertions.assertThat(patientsResult.size()).isEqualTo(1);
        Assertions.assertThat(patientsResult.get(0).getName()).isEqualTo("patient");

    }


    @Test
    void shouldUpdatePatient() {
        PatientDTO patient = PatientDTO.builder().name("patientUpdated").build();
        Patient patientUpdated = new Patient();
        patientUpdated.setName("patientUpdated");


        Mockito.when(patientMapper.toPatient(patient)).thenReturn(patientUpdated);
        Mockito.when(patientRepository.save(patientUpdated)).thenReturn(patientUpdated);
        Mockito.when(patientMapper.toPatientDTO(patientUpdated)).thenReturn(patient);

        var patientsResult = patientService.editPatient(patient);

        Assertions.assertThat(patientsResult.getName()).isEqualTo("patientUpdated");

    }
}
