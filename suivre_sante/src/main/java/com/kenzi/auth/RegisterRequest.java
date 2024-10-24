package com.kenzi.auth;

import com.kenzi.enumeration.Role;
import com.kenzi.enumeration.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;

    // Champs communs
    private Date dateOfBirth;
    private Genre genre;

    // Champs spécifiques au rôle Patient
    private String medicalHistory;
    private String insuranceInfo;

    // Champs spécifiques au rôle Doctor
    private String telephone;
    private String specialite;
    private String adresse;
    private String experience;
    private String hopital;
    private String biographie;
    private String photoProfil;
}