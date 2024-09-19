package com.kenzi.services.impl;

import com.kenzi.dtos.ActivityDto;
import com.kenzi.models.Activity;
import com.kenzi.models.User;
import com.kenzi.repositories.ActivityRepository;
import com.kenzi.repositories.UserRepository;
import com.kenzi.services.IActivityService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements IActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }



    @Override

    public Activity saveActivity(Activity activity) {
            // Vérifiez si l'utilisateur existe
            Optional<User> userOptional = userRepository.findById(activity.getUser().getId());
            if (!userOptional.isPresent()) {
                throw new RuntimeException("User not found");
            }
            // Associez l'utilisateur à l'activité
            activity.setUser(userOptional.get());
            // Enregistrez l'activité
            return activityRepository.save(activity);

    }

    @Override
    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    @Override
    public List<Activity> getAllActivitiesByUserId(Long userId) {
        return activityRepository.findByUserId(userId);
    }



    @Override
    public List<Activity> getHighCalorieActivities(Long userId, int minCalories) {
        return activityRepository.findHighCalorieActivities(userId, minCalories);
    }

    @Override
    public Double getAverageDurationForActivityType(Long userId, String type) {
        return activityRepository.getAverageDurationByUserAndType(userId, type) ;
    }

    @Override
    public List<Activity> getTopCalorieBurningActivities(Long userId, int limit) {
        return activityRepository.findTopCalorieBurningActivities(userId, limit);
    }


   /* public ActivityDto getActivityStatistics(Long userId, LocalDate startDate, LocalDate endDate) {
        return activityRepository.getActivityStatistics(userId, startDate.atStartOfDay(), endDate.atStartOfDay());
    }*/

    @Override
    public List<Object[]> getMostActiveDays(Long userId, int limit) {
        return null;
    }

    @Override
    public List<Object[]> getRecurringActivities(Long userId, int minOccurrences) {
        return activityRepository.findRecurringActivities(userId, minOccurrences);
    }

    @Override
    public void deleteActivity(Long id) {
     activityRepository.deleteById(id);
    }

    @Override
    public Activity updateActivity(Long id, Activity activityDetails) {
         Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found for this id :: " + id));

        activity.setType(activityDetails.getType());
        activity.setStartTime(activityDetails.getStartTime());
        activity.setEndTime(activityDetails.getEndTime());
        activity.setDuration(activityDetails.getDuration());
        activity.setDistance(activityDetails.getDistance());
        activity.setCalories(activityDetails.getCalories());
        activity.setNotes(activityDetails.getNotes());

        return activityRepository.save(activity);
    }
}
