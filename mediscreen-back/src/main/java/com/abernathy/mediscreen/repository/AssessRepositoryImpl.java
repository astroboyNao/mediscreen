package com.abernathy.mediscreen.repository;


import com.abernathy.mediscreen.model.domain.Note;
import com.abernathy.mediscreen.model.dto.AssessDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class AssessRepositoryImpl implements AssessRepository {
    private RestTemplate restTemplate;
    private String urlBaseApiAssess;

    public AssessRepositoryImpl(RestTemplate restTemplate, @Value("${urlBaseApiAssess:http://localhost:8083/api}") String urlBaseApiAssess) {
        this.restTemplate = restTemplate;
        this.urlBaseApiAssess = urlBaseApiAssess;
    }

    public AssessDTO getAssess(Long patientId) {
        return this.restTemplate.getForObject(this.urlBaseApiAssess + "/assess/" + patientId, AssessDTO.class);
    }

}