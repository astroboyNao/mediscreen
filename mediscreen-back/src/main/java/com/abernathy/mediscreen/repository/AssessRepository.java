package com.abernathy.mediscreen.repository;

import com.abernathy.mediscreen.model.dto.AssessDTO;

public interface AssessRepository {
    public AssessDTO getAssess(Long patientId);
}
