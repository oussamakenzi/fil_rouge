package com.kenzi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String telephone;
    private String specialite;
    private String adresse;
    private String dateNaissance;
    private String genre;
    private String experience;
    private String hopital;
    private String biographie;
    private String photoProfil;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private List<Certifications> certifications = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private List<Appointment> appointments = new ArrayList<>();
}
