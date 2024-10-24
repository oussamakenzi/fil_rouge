package com.kenzi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Certifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String certificationName;
    private String issuingOrganization;  // Organisation qui a délivré la certification
    private String issueDate;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Patient patient;
}
