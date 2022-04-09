package com.abernathy.assess.service;

import com.abernathy.assess.model.domain.Patient;
import com.abernathy.assess.model.dto.AssessDTO;
import com.abernathy.assess.repository.NoteRepository;
import com.abernathy.assess.repository.PatientRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class AssessService {
    private final static String searchTerm = "Hémoglobine A1C|Microalbumine|Taille|Poids|Fumeur|Anormal|Cholestérol|Vertige|Rechute|Réaction|Anticorps";
    private PatientRepository patientRepository;
    private NoteRepository noteRepository;

    public AssessDTO get(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("not Found"));
        Long countTerms = noteRepository.countByPropertiesPatientIdAndSearchText(patientId, searchTerm);
        System.out.println("countTerms " + countTerms);
        var old = LocalDate.now().getYear() - patient.getDob().getYear();
        System.out.println("old " + old);

        String status = calculateStatus(patient, countTerms, old);
        return AssessDTO.builder().patientName(patient.getName()).old(old).status(status).build();
    }

    private String calculateStatus(Patient patient, Long countTerms, int old) {
        return (old > 30) ?
                calculateStatusByIntervalls(countTerms, 7, 5, 1) :
                calculateStatusBySex(patient, countTerms);
    }

    private String calculateStatusBySex(Patient patient, Long countTerms) {
        return (patient.getSex() == 'M') ?
                calculateStatusByIntervalls(countTerms, 4, 2, 2) :
                calculateStatusByIntervalls(countTerms, 6, 3, 2);
    }

    ;

    private String calculateStatusByIntervalls(Long countTerms, int intervallEarlyInset, int intervallDanger, int intervallBorderLine) {
        System.out.println("coutTermes" + countTerms + "earlyInset" + intervallEarlyInset + "intervallDanger" + intervallDanger + "intervallBorderLine" + intervallBorderLine);
        String status = "NONE";
        if (countTerms > intervallEarlyInset) {
            status = "EARLY INSET";
        } else if (countTerms > intervallDanger) {
            status = "DANGER";
        } else if (countTerms > intervallBorderLine) {
            status = "BORDERLINE";
        }
        return status;
    }
}
