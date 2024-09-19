package com.kenzi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ActivityDto {
    private Long userId;
    private Long totalActivities;
    private Double totalDistance;
    private Integer totalCalories;
    public ActivityDto(Long userId, Long totalActivities, Double totalDistance, Integer totalCalories) {
        this.userId = userId;
        this.totalActivities = totalActivities;
        this.totalDistance = totalDistance;
        this.totalCalories = totalCalories;
    }
}
