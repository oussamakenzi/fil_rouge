package com.kenzi.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mood;

    private String notes;

    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Patient patient;


}
