package com.abernathy.mediscreen.repository;

import com.abernathy.mediscreen.model.domain.Note;

public interface NoteRepository {
    public Note[] getAllForPatient(Long patientId);
}
