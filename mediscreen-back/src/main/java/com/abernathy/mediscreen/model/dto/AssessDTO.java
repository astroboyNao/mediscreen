package com.abernathy.mediscreen.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class AssessDTO {
    private String patientName;
    private int old;
    private String status;
}
