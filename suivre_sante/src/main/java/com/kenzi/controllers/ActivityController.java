package com.kenzi.controllers;

import com.kenzi.dtos.ActivityDto;
import com.kenzi.models.Activity;
import com.kenzi.services.impl.ActivityServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/activities")
public class ActivityController {


    private final ActivityServiceImpl activityService;

    public ActivityController(ActivityServiceImpl activityService) {
        this.activityService = activityService;
    }


    @PostMapping("/add")
        public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
            return ResponseEntity.ok(activityService.saveActivity(activity));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
            return activityService.getActivityById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping("/user/{userId}")
        public ResponseEntity<List<Activity>> getAllActivitiesByUserId(@PathVariable Long userId) {
            return ResponseEntity.ok(activityService.getAllActivitiesByUserId(userId));
        }



        @GetMapping("/user/{userId}/high-calorie")
        public ResponseEntity<List<Activity>> getHighCalorieActivities(
                @PathVariable Long userId,
                @RequestParam int minCalories) {
            return ResponseEntity.ok(activityService.getHighCalorieActivities(userId, minCalories));
        }

        @GetMapping("/user/{userId}/average-duration")
        public ResponseEntity<Double> getAverageDurationForActivityType(
                @PathVariable Long userId,
                @RequestParam String type) {
            return ResponseEntity.ok(activityService.getAverageDurationForActivityType(userId, type));
        }

        @GetMapping("/user/{userId}/top-calorie-burning")
        public ResponseEntity<List<Activity>> getTopCalorieBurningActivities(
                @PathVariable Long userId,
                @RequestParam(defaultValue = "5") int limit) {
            return ResponseEntity.ok(activityService.getTopCalorieBurningActivities(userId, limit));
        }

      /*  @GetMapping("/user/{userId}/statistics")
        public ResponseEntity<ActivityDto> getActivityStatistics(
                @PathVariable Long userId,
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
            return ResponseEntity.ok(activityService.getActivityStatistics(userId, startDate, endDate));
        }*/

        @GetMapping("/user/{userId}/most-active-days")
        public ResponseEntity<List<Object[]>> getMostActiveDays(
                @PathVariable Long userId,
                @RequestParam(defaultValue = "5") int limit) {
            return ResponseEntity.ok(activityService.getMostActiveDays(userId, limit));
        }

        @GetMapping("/user/{userId}/recurring-activities")
        public ResponseEntity<List<Object[]>> getRecurringActivities(
                @PathVariable Long userId,
                @RequestParam(defaultValue = "3") int minOccurrences) {
            return ResponseEntity.ok(activityService.getRecurringActivities(userId, minOccurrences));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity activityDetails) {
            return ResponseEntity.ok(activityService.updateActivity(id, activityDetails));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
            activityService.deleteActivity(id);
            return ResponseEntity.ok().build();
        }
}


