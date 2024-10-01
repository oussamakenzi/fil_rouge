package com.kenzi.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NutritionDTO {
    private Long id;
    private String mealType;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
    private LocalDate date;
    private Long userId;
}
