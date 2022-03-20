package com.abernathy.mediscreen.repository;

import com.abernathy.mediscreen.model.domain.Note;
import com.abernathy.mediscreen.model.dto.NoteDTO;

public interface NoteRepository {
    public Note[] getAllForPatient(Long patientId);

    public Note addNoteForPatient(Note note, Long patientId);

    public Note updateNote(Note note);
}
