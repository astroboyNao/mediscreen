package com.abernathy.mediscreen.repository;


import com.abernathy.mediscreen.model.domain.Note;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class NoteRepositoryImpl implements NoteRepository {
    private RestTemplate restTemplate;
    private String urlBaseApiNotes;

    public NoteRepositoryImpl(RestTemplate restTemplate, @Value("${urlBaseApiNotes:http://localhost:8082/api}") String urlBaseApiNotes) {
        this.restTemplate = restTemplate;
        this.urlBaseApiNotes = urlBaseApiNotes;
    }

    public Note[] getAllForPatient(Long patientId) {
        return this.restTemplate.getForObject(this.urlBaseApiNotes + "/notes/" + patientId, Note[].class);
    }
}