package com.abernathy.mediscreen.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Note {
    private String id;
    private Long patientId;
    private String riskLevel;
    private String note;
}
