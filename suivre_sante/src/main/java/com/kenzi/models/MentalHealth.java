package com.kenzi.models;

import com.kenzi.enumeration.Mood;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MentalHealth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Mood mood; // Enum pour représenter l'humeur

    private int stressLevel; // Niveau de stress sur une échelle de 1 à 10

    private String notes; // Notes ou observations sur la santé mentale

    private LocalDate date; // Date de l'entrée
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
