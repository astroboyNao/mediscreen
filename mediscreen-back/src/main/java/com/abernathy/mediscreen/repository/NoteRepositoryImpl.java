package com.abernathy.mediscreen.repository;


import com.abernathy.mediscreen.model.domain.Note;
import com.abernathy.mediscreen.model.dto.NoteDTO;
import com.abernathy.mediscreen.model.dto.PatientDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class NoteRepositoryImpl implements NoteRepository {
    private RestTemplate restTemplate;
    private String urlBaseApiNotes;

    public NoteRepositoryImpl(RestTemplate restTemplate, @Value("${urlBaseApiNotes:http://localhost:8084/api}") String urlBaseApiNotes) {
        this.restTemplate = restTemplate;
        this.urlBaseApiNotes = urlBaseApiNotes;
    }

    public Note[] getAllForPatient(Long patientId) {
        return this.restTemplate.getForObject(this.urlBaseApiNotes + "/notes/" + patientId, Note[].class);
    }

    public Note addNoteForPatient(Note note, Long patientId) {
        return this.restTemplate.postForObject(this.urlBaseApiNotes + "/note/" + patientId, note, Note.class);
    }

    public Note updateNote(Note note) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<Note>(note, headers);
        return this.restTemplate.exchange(this.urlBaseApiNotes + "/note/" + note.getId() + "/patient/" + note.getPatientId(), HttpMethod.PUT, entity, Note.class).getBody();
    }
}