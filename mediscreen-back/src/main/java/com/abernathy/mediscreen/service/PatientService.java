package com.abernathy.mediscreen.service;

import com.abernathy.mediscreen.model.domain.Note;
import com.abernathy.mediscreen.model.dto.AssessDTO;
import com.abernathy.mediscreen.model.dto.NoteDTO;
import com.abernathy.mediscreen.model.dto.PatientDTO;
import com.abernathy.mediscreen.model.mapper.NoteMapper;
import com.abernathy.mediscreen.model.mapper.PatientMapper;
import com.abernathy.mediscreen.repository.AssessRepository;
import com.abernathy.mediscreen.repository.NoteRepository;
import com.abernathy.mediscreen.repository.NoteRepositoryImpl;
import com.abernathy.mediscreen.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService {
    PatientRepository patientRepository;
    NoteRepository noteRepository;
    PatientMapper patientMapper;
    NoteMapper noteMapper;
    AssessRepository assessRepository;

    public List<PatientDTO> getAll() {
        return patientMapper.toPatientDTOs(patientRepository.findAll());
    }

    public PatientDTO editPatient(PatientDTO patient) {
        return patientMapper.toPatientDTO(patientRepository.save(patientMapper.toPatient(patient)));
    }

    public PatientDTO addPatient(PatientDTO patient) {
        return patientMapper.toPatientDTO(patientRepository.save(patientMapper.toPatient(patient)));
    }

    public List<NoteDTO> getAllNotes(Long idPatient) {
        return Arrays.stream(noteRepository.getAllForPatient(idPatient)).map(note -> noteMapper.toNoteDTO(note)).collect(Collectors.toList());
    }

    public NoteDTO addNoteForPatient(NoteDTO noteDTO, Long patientId) {
        Note note = noteMapper.toNote(noteDTO);
        note.setId(null);
        return noteMapper.toNoteDTO(noteRepository.addNoteForPatient(note, patientId));
    }

    public NoteDTO updateNoteForPatient(NoteDTO noteDTO, Long patientId) {
        Note note = noteMapper.toNote(noteDTO);
        note.setPatientId(patientId);
        return noteMapper.toNoteDTO(noteRepository.updateNote(note));
    }

    public AssessDTO getAssess(Long patientId) {
        return assessRepository.getAssess(patientId);
    }
}
