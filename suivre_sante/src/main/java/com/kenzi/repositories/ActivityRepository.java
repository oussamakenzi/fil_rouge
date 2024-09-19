package com.kenzi.repositories;

import com.kenzi.dtos.ActivityDto;
import com.kenzi.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByUserId(Long userId);

    @Query("SELECT a FROM Activity a WHERE a.user.id = :userId AND a.calories > :minCalories")
    List<Activity> findHighCalorieActivities(@Param("userId") Long userId, @Param("minCalories") int minCalories);

    @Query("SELECT AVG(a.duration) FROM Activity a WHERE a.user.id = :userId AND a.type = :type")
    Double getAverageDurationByUserAndType(@Param("userId") Long userId, @Param("type") String type);

    @Query(value = "SELECT * FROM activities WHERE user_id = :userId ORDER BY calories DESC LIMIT :limit", nativeQuery = true)
    List<Activity> findTopCalorieBurningActivities(@Param("userId") Long userId, @Param("limit") int limit);

    @Query("SELECT a.type, COUNT(a) as typeCount FROM Activity a " +
            "WHERE a.user.id = :userId GROUP BY a.type HAVING COUNT(a) > :minOccurrences " +
            "ORDER BY typeCount DESC")
    List<Object[]> findRecurringActivities(@Param("userId") Long userId, @Param("minOccurrences") int minOccurrences);

   /* @Query("SELECT new com.kenzi.dtos.ActivityDto(a.user.id, COUNT(a.id), SUM(a.distance), SUM(a.calories)) " +
            "FROM Activity a " +
            "WHERE a.user.id = :userId AND a.startTime BETWEEN :startDate AND :endDate " +
            "GROUP BY a.user.id")
    ActivityDto getActivityStatistics(@Param("userId") Long userId,
                                      @Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);
*/
}

