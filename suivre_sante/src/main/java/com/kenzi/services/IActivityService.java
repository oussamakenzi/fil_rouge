package com.kenzi.services;

import com.kenzi.dtos.ActivityDto;
import com.kenzi.models.Activity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IActivityService {
        Activity saveActivity(Activity activity);
        Optional<Activity> getActivityById(Long id);
        List<Activity> getAllActivitiesByUserId(Long userId);
        List<Activity> getHighCalorieActivities(Long userId, int minCalories);

        Double getAverageDurationForActivityType(Long userId, String type);
        List<Activity> getTopCalorieBurningActivities(Long userId, int limit);
       // ActivityDto getActivityStatistics(Long userId, LocalDate startDate, LocalDate endDate);
        List<Object[]> getMostActiveDays(Long userId, int limit);
        List<Object[]> getRecurringActivities(Long userId, int minOccurrences);
        void deleteActivity(Long id);
        Activity updateActivity(Long id, Activity activityDetails);
        private void method3(){
            System.out.println("hello");
        }
    }

