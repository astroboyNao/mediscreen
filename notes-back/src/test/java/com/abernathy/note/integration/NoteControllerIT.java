package com.abernathy.note.integration;

import com.abernathy.notes.NotesApplication;
import com.abernathy.notes.configuration.ApplicationConfiguration;
import com.abernathy.notes.model.domain.Note;
import com.abernathy.notes.model.dto.NoteDTO;
import com.abernathy.notes.repository.NoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {NotesApplication.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureDataMongo
@ActiveProfiles("test")
public class NoteControllerIT {
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testGetAllNotes() {

        HttpEntity entity = new HttpEntity<>(null, headers);

        ResponseEntity responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/api/notes/1"), HttpMethod.GET, entity, Note[].class);

        Assertions.assertNotNull(responseEntity);
    }

    @Test
    public void addPatient() {
        NoteDTO note = new NoteDTO();
        note.setNote("note");
        note.setRiskLevel("riskLevel");

        HttpEntity entity = new HttpEntity<NoteDTO>(note, headers);

        ResponseEntity responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/api/note/1"), HttpMethod.POST, entity, NoteDTO.class);

        Assertions.assertNotNull(responseEntity);

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
