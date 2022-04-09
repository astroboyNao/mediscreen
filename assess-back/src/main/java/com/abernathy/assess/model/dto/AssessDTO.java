package com.abernathy.assess.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssessDTO {
    private String patientName;
    private int old;
    private String status;
}
