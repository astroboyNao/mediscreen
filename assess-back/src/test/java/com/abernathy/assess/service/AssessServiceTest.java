package com.abernathy.assess.service;

import com.abernathy.assess.model.domain.Patient;
import com.abernathy.assess.repository.NoteRepository;
import com.abernathy.assess.repository.PatientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class AssessServiceTest {
    private final static String searchTerm = "Hémoglobine A1C|Microalbumine|Taille|Poids|Fumeur|Anormal|Cholestérol|Vertige|Rechute|Réaction|Anticorps";
    PatientRepository patientRepository;
    NoteRepository noteRepository;
    AssessService assessService;

    private static Stream<Arguments> provideTestForNoRisk() {
        return Stream.of(
                Arguments.of('F', 10, 0L, "NONE"),
                Arguments.of('M', 10, 0L, "NONE"),
                Arguments.of('F', 10, 1L, "NONE"),
                Arguments.of('M', 10, 1L, "NONE"),
                Arguments.of('F', 30, 0L, "NONE"),
                Arguments.of('M', 30, 0L, "NONE"),
                Arguments.of('F', 30, 1L, "NONE"),
                Arguments.of('M', 30, 1L, "NONE"),
                Arguments.of('F', 31, 0L, "NONE"),
                Arguments.of('M', 31, 0L, "NONE"),
                Arguments.of('F', 31, 1L, "NONE"),
                Arguments.of('M', 31, 1L, "NONE"),

                Arguments.of('F', 29, 2L, "NONE"),
                Arguments.of('M', 29, 2L, "NONE"),
                Arguments.of('F', 30, 2L, "NONE"),
                Arguments.of('M', 30, 2L, "NONE"),
                Arguments.of('F', 31, 2L, "BORDERLINE"),
                Arguments.of('M', 31, 2L, "BORDERLINE"),


                Arguments.of('F', 29, 3L, "BORDERLINE"),
                Arguments.of('F', 29, 4L, "DANGER"),
                Arguments.of('M', 29, 3L, "DANGER"),

                Arguments.of('F', 30, 3L, "BORDERLINE"),
                Arguments.of('F', 30, 4L, "DANGER"),
                Arguments.of('M', 30, 3L, "DANGER"),

                Arguments.of('M', 31, 5L, "BORDERLINE"),
                Arguments.of('F', 31, 5L, "BORDERLINE"),

                Arguments.of('M', 31, 6L, "DANGER"),
                Arguments.of('F', 31, 6L, "DANGER"),

                Arguments.of('M', 29, 5L, "EARLY INSET"),
                Arguments.of('F', 29, 7L, "EARLY INSET"),
                Arguments.of('M', 30, 5L, "EARLY INSET"),
                Arguments.of('F', 30, 7L, "EARLY INSET"),


                Arguments.of('M', 31, 8L, "EARLY INSET"),
                Arguments.of('F', 31, 8L, "EARLY INSET"),
                Arguments.of('M', 31, 9L, "EARLY INSET"),
                Arguments.of('F', 31, 9L, "EARLY INSET")

        );
    }

    @BeforeEach
    void init() {
        noteRepository = Mockito.mock(NoteRepository.class);
        patientRepository = Mockito.mock(PatientRepository.class);
        assessService = new AssessService(patientRepository, noteRepository);
    }

    @ParameterizedTest
    @MethodSource("provideTestForNoRisk")
    void shouldGetStatusRisk(char sex, int old, Long numberOfTerm, String expectedStatus) {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("name");
        patient.setSex(sex);
        patient.setDob(LocalDate.now().minus(old, ChronoUnit.YEARS));
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Mockito.when(noteRepository.countByPropertiesPatientIdAndSearchText(1L, searchTerm)).thenReturn(numberOfTerm);

        var assess = assessService.get(1L);

        Assertions.assertThat(assess.getStatus()).isEqualTo(expectedStatus);

    }
}
