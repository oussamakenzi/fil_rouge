// Doctor.java
package com.kenzi.models;

import com.kenzi.enumeration.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("DOCTOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Doctor extends User {

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String specialite;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_naissance")
    private Date dateNaissance;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String experience;

    private String hopital;

    @Column(columnDefinition = "TEXT")
    private String biographie;

    private String photoProfil;

    /* 
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certifications> certifications = new ArrayList<>();
    */
}
