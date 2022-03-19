package com.abernathy.notes.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("notes")
@Data
@AllArgsConstructor
public class Note {
    @Id
    private String id;

    private Long patientId;
    private String riskLevel;
    private String note;
}
