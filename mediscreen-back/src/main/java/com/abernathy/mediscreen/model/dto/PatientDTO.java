package com.abernathy.mediscreen.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Jacksonized
@Getter
public class PatientDTO {
    private Long id;
    private String name;
    private String firstName;
    private String family;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private char sex;
    private String address;
    private String phone;
}
