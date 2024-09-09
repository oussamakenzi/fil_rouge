package com.kenzi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActivityDto {
    private Long count;         // Nombre total d'activités
    private Integer totalDuration; // Durée totale des activités (en minutes)
    private Double averageCalories; // Calories moyennes brûlées par activité

}
