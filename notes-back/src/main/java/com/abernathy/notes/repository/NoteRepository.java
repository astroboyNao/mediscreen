package com.abernathy.notes.repository;

import com.abernathy.notes.model.domain.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    public List<Note> findByPatientId(Long patientId);
}