package com.kenzi.repositories;

import com.kenzi.models.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
    List<Nutrition> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    List<Nutrition> findByUserId(Long userId);
}