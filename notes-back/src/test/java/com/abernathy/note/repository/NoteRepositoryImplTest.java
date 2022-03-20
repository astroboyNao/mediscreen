package com.abernathy.note.repository;

import com.abernathy.notes.NotesApplication;
import com.abernathy.notes.model.domain.Note;
import com.abernathy.notes.repository.NoteRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {NoteRepository.class, NotesApplication.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@DataMongoTest
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
    public void getAllNoteForPatient() {

        Note note = new Note();
        note.setNote("note");
        note.setPatientId(1L);
        noteRepository.save(note);
        List<Note> noteResult = noteRepository.findByPatientId(1L);
        Assertions.assertEquals(noteResult.size(), 1);
    }

    @Test
    public void addORUpdateNoteForPatient() {
        Note note = new Note();
        note.setNote("note");
        Note noteResult = noteRepository.save(note);
        Assertions.assertEquals(noteResult.getNote(), "note");
    }
}