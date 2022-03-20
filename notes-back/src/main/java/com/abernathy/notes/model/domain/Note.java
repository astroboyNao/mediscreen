package com.abernathy.notes.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    private String id;

    private Long patientId;
    private String riskLevel;
    private String note;
}
