package com.abernathy.mediscreen.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PATIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String firstName;
    @Column
    private String family;
    @Column
    private LocalDate dob; //YYYY-MM-dd
    @Column
    private char sex;
    @Column
    private String address;
    @Column
    private String phone; //format=100-222-3333
}
