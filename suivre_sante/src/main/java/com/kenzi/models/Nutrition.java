package com.kenzi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
