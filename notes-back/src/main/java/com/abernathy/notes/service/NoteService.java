package com.abernathy.notes.service;

import com.abernathy.notes.model.domain.Note;
import com.abernathy.notes.model.dto.NoteDTO;
import com.abernathy.notes.model.mapper.NoteMapper;
import com.abernathy.notes.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {
    NoteRepository noteRepository;
    NoteMapper noteMapper;

    public List<NoteDTO> getAllForPatientId(Long patientId) {
        return noteMapper.toNoteDTOs(noteRepository.findByPatientId(patientId));
    }

    public NoteDTO addNoteForPatientId(NoteDTO noteDTO, Long patientId) {
        Note note = noteMapper.toNote(noteDTO);
        note.setPatientId(patientId);
        return noteMapper.toNoteDTO(noteRepository.save(note));
    }

    public NoteDTO updateNote(NoteDTO noteDTO, Long patientId) {
        Note note = noteMapper.toNote(noteDTO);
        note.setPatientId(patientId);
        return noteMapper.toNoteDTO(noteRepository.save(note));
    }
}
