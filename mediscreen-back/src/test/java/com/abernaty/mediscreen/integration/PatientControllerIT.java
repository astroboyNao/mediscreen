package com.abernaty.mediscreen.integration;

import com.abernathy.mediscreen.MediscreenApplication;
import com.abernathy.mediscreen.configuration.ApplicationConfiguration;
import com.abernathy.mediscreen.model.dto.PatientDTO;
import com.abernathy.mediscreen.repository.PatientRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MediscreenApplication.class, ApplicationConfiguration.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
@ActiveProfiles("test")
public class PatientControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    @Sql({"/insert-data.sql"})
    public void testGetAllPatients() {
        HttpEntity entity = new HttpEntity<>(null, headers);

        ResponseEntity responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/api/patients"), HttpMethod.GET, entity, PatientDTO[].class);

        Assertions.assertNotNull(responseEntity);

    }

    @Test
    @Sql({"/insert-data.sql"})
    public void updatePatient() {
        PatientDTO patient = PatientDTO.builder().id(1L).name("patientUpdated").build();

        HttpEntity entity = new HttpEntity<PatientDTO>(patient, headers);

        ResponseEntity responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/api/patient"), HttpMethod.PUT, entity, PatientDTO.class);

        Assertions.assertNotNull(responseEntity);

    }

    @Test
    @Sql({"/insert-data.sql"})
    public void addPatient() {
        PatientDTO patient = PatientDTO.builder().id(1L).name("patientUpdated").build();

        HttpEntity entity = new HttpEntity<PatientDTO>(patient, headers);

        ResponseEntity responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/api/patient"), HttpMethod.POST, entity, PatientDTO.class);

        Assertions.assertNotNull(responseEntity);

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
