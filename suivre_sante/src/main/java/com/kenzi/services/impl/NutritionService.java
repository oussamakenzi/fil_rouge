package com.kenzi.services.impl;


import com.kenzi.dtos.NutritionDTO;
import com.kenzi.models.Nutrition;
import com.kenzi.models.User;
import com.kenzi.repositories.NutritionRepository;
import com.kenzi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutritionService {

    private final NutritionRepository nutritionRepository;
    private final UserRepository userRepository;

    @Autowired
    public NutritionService(NutritionRepository nutritionRepository, UserRepository userRepository) {
        this.nutritionRepository = nutritionRepository;
        this.userRepository = userRepository;
    }

    public NutritionDTO addNutritionToUser(NutritionDTO nutritionDTO) {
        User user = userRepository.findById(nutritionDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Nutrition nutrition = convertToEntity(nutritionDTO);
        nutrition.setUser(user);
        Nutrition savedNutrition = nutritionRepository.save(nutrition);
        return convertToDTO(savedNutrition);
    }

    public List<NutritionDTO> getNutritionByUserIdAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        return nutritionRepository.findByUserIdAndDateBetween(userId, startDate, endDate)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<NutritionDTO> getNutritionByUserId(Long userId) {
        return nutritionRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private NutritionDTO convertToDTO(Nutrition nutrition) {
        NutritionDTO dto = new NutritionDTO();
        dto.setId(nutrition.getId());
        dto.setMealType(nutrition.getMealType());
        dto.setCalories(nutrition.getCalories());
        dto.setProtein(nutrition.getProtein());
        dto.setCarbs(nutrition.getCarbs());
        dto.setFat(nutrition.getFat());
        dto.setDate(nutrition.getDate());
        dto.setUserId(nutrition.getUser().getId());
        return dto;
    }

    private Nutrition convertToEntity(NutritionDTO dto) {
        Nutrition nutrition = new Nutrition();
        nutrition.setMealType(dto.getMealType());
        nutrition.setCalories(dto.getCalories());
        nutrition.setProtein(dto.getProtein());
        nutrition.setCarbs(dto.getCarbs());
        nutrition.setFat(dto.getFat());
        nutrition.setDate(dto.getDate());
        return nutrition;
    }
}
