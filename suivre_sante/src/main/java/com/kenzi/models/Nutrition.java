package com.kenzi.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Nutrition {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mealType;

    private int calories;

    private int protein;

    private int carbs;

    private int fat;

    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
