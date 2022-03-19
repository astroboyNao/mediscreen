package com.abernathy.notes.model.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
public class NoteDTO {
    private String id;
    private String riskLevel;
    private String note;
}
