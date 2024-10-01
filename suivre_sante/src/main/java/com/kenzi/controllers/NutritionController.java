package com.kenzi.controllers;

import com.kenzi.dtos.NutritionDTO;
import com.kenzi.services.impl.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/nutrition")
public class NutritionController {

    private final NutritionService nutritionService;

    @Autowired
    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }

    @PostMapping
    public ResponseEntity<NutritionDTO> addNutrition(@RequestBody NutritionDTO nutritionDTO) {
        NutritionDTO savedNutrition = nutritionService.addNutritionToUser(nutritionDTO);
        return new ResponseEntity<>(savedNutrition, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NutritionDTO>> getNutritionByUserId(@PathVariable Long userId) {
        List<NutritionDTO> nutritionList = nutritionService.getNutritionByUserId(userId);
        return ResponseEntity.ok(nutritionList);
    }

    @GetMapping("/user/{userId}/dateRange")
    public ResponseEntity<List<NutritionDTO>> getNutritionByUserIdAndDateRange(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<NutritionDTO> nutritionList = nutritionService.getNutritionByUserIdAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(nutritionList);
    }
}