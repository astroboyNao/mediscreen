package com.abernathy.assess.repository;

import com.abernathy.assess.AssessApplication;
import com.abernathy.assess.configuration.ApplicationConfiguration;
import com.abernathy.assess.model.domain.Note;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {NoteRepository.class, AssessApplication.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@DirtiesContext
public class NoteRepositoryImplTest {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    private String collectionName = "note";

    @BeforeEach
    public void setup() {
        mongoTemplate.createCollection("note");
    }

    @AfterEach
    public void clean() {
        mongoTemplate.dropCollection("note");
    }

    @Test
    public void countByPropertiesPatientIdAndSearchTextForOneNote() {
        Note note = new Note();
        note.setNote("Microalbumine Microalbumine");
        note.setPatientId(99999L);
        noteRepository.save(note);
        var count = noteRepository.countByPropertiesPatientIdAndSearchText(99999L, "Microalbumine");
        noteRepository.delete(note);
        Assertions.assertEquals(count, 2L);
    }

    @Test
    public void countByPropertiesPatientIdAndSearchTextForMultipleNote() {
        Note note = new Note();
        note.setNote("Microalbumine Microalbumine");
        note.setPatientId(99999L);
        noteRepository.save(note);
        Note note2 = new Note();
        note2.setNote("Microalbumine");
        note2.setPatientId(99999L);
        noteRepository.save(note2);
        var count = noteRepository.countByPropertiesPatientIdAndSearchText(99999L, "Microalbumine");

        noteRepository.delete(note);
        noteRepository.delete(note2);
        Assertions.assertEquals(count, 3L);
    }
}