package com.abernaty.mediscreen.service;

import com.abernathy.mediscreen.model.domain.Note;
import com.abernathy.mediscreen.model.domain.Patient;
import com.abernathy.mediscreen.model.dto.NoteDTO;
import com.abernathy.mediscreen.model.dto.PatientDTO;
import com.abernathy.mediscreen.model.mapper.NoteMapper;
import com.abernathy.mediscreen.model.mapper.PatientMapper;
import com.abernathy.mediscreen.repository.AssessRepository;
import com.abernathy.mediscreen.repository.NoteRepositoryImpl;
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
    NoteRepositoryImpl noteRepository;
    AssessRepository assessRepository;
    NoteMapper noteMapper;

    @BeforeEach
    void init() {
        patientRepository = Mockito.mock(PatientRepository.class);
        patientMapper = Mockito.mock(PatientMapper.class);
        noteRepository = Mockito.mock(NoteRepositoryImpl.class);
        noteMapper = Mockito.mock(NoteMapper.class);
        patientService = new PatientService(patientRepository, noteRepository, patientMapper, noteMapper, assessRepository);
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

    @Test
    void shouldAddPatient() {
        PatientDTO patient = PatientDTO.builder().name("patientInserted").build();
        Patient patientUpdated = new Patient();
        patientUpdated.setName("patientInserted");

        Mockito.when(patientMapper.toPatient(patient)).thenReturn(patientUpdated);
        Mockito.when(patientRepository.save(patientUpdated)).thenReturn(patientUpdated);
        Mockito.when(patientMapper.toPatientDTO(patientUpdated)).thenReturn(patient);

        var patientsResult = patientService.addPatient(patient);

        Assertions.assertThat(patientsResult.getName()).isEqualTo("patientInserted");

    }

    @Test
    void shouldGetNotesForPatient() {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNote("com.abernathy.note");
        noteDTO.setId("idNote");
        noteDTO.setRiskLevel("riskLevel");

        Note note = new Note();
        note.setNote("com.abernathy.note");
        note.setId("idNote");
        note.setRiskLevel("riskLevel");
        Note[] notes = {note};

        Mockito.when(noteRepository.getAllForPatient(1L)).thenReturn(notes);
        Mockito.when(noteMapper.toNoteDTO(note)).thenReturn(noteDTO);

        var patientsResult = patientService.getAllNotes(1L);

        Assertions.assertThat(patientsResult.size()).isEqualTo(1);
        Assertions.assertThat(patientsResult.get(0).getNote()).isEqualTo("com.abernathy.note");

    }

    @Test
    void addNotesForPatient() {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNote("com.abernathy.note");
        noteDTO.setId("idNote");
        noteDTO.setRiskLevel("riskLevel");

        Note note = new Note();
        note.setNote("com.abernathy.note");
        note.setId("idNote");
        note.setRiskLevel("riskLevel");
        Note[] notes = {note};

        Mockito.when(noteRepository.addNoteForPatient(note, 1L)).thenReturn(note);
        Mockito.when(noteMapper.toNoteDTO(note)).thenReturn(noteDTO);
        Mockito.when(noteMapper.toNote(noteDTO)).thenReturn(note);

        var noteResult = patientService.addNoteForPatient(noteDTO, 1L);

        Assertions.assertThat(noteResult.getNote()).isEqualTo("com.abernathy.note");

    }
}
