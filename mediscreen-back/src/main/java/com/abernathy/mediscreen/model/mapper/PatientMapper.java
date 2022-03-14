package com.abernathy.mediscreen.model.mapper;

import com.abernathy.mediscreen.model.domain.Patient;
import com.abernathy.mediscreen.model.dto.PatientDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toPatientDTO(Patient patient);
    List<PatientDTO> toPatientDTOs(List<Patient> patients);

    Patient toPatient(PatientDTO patientDTO);
}
