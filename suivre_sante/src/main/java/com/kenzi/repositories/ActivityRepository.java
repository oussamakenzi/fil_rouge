package com.kenzi.repositories;

import com.kenzi.dtos.ActivityDto;
import com.kenzi.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    // Méthodes de base dérivées des noms de méthode
    List<Activity> findByUserId(Long userId);
    @Query("SELECT a FROM Activity a WHERE a.user.id = :userId AND a.calories > :minCalories")
    List<Activity> findHighCalorieActivities(@Param("userId") Long userId, @Param("minCalories") int minCalories);

    @Query("SELECT AVG(a.duration) FROM Activity a WHERE a.user.id = :userId AND a.type = :type")
    Double getAverageDurationByUserAndType(@Param("userId") Long userId, @Param("type") String type);

    // Méthode avec une requête native SQL
    @Query(value = "SELECT * FROM activities WHERE user_id = :userId ORDER BY calories DESC LIMIT :limit", nativeQuery = true)
    List<Activity> findTopCalorieBurningActivities(@Param("userId") Long userId, @Param("limit") int limit);


    // Méthode pour trouver les activités récurrentes
    @Query("SELECT a.type, COUNT(a) as typeCount FROM Activity a " +
            "WHERE a.user.id = :userId GROUP BY a.type HAVING typeCount > :minOccurrences " +
            "ORDER BY typeCount DESC")
    List<Object[]> findRecurringActivities(@Param("userId") Long userId, @Param("minOccurrences") int minOccurrences);


    ActivityDto getActivityStatistics(Long userId, LocalDate startDate, LocalDate endDate);
}
