package com.kenzi.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String specialite;
    private String adresse;
    private String dateNaissance;
    private String genre;
    private String experience;
    private String hopital;
    private String biographie;
    private String photoProfil;
    private List<Long> certificationIds;
    private List<Long> medicalRecordIds;
    private List<Long> appointmentIds;

}
