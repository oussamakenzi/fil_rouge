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

        /**
         * Récupère les activités d'un utilisateur dans une plage de dates donnée.
         *
         * @param userId L'ID de l'utilisateur
         * @param startDate La date de début de la plage
         * @param endDate La date de fin de la plage
         * @return Une liste des activités de l'utilisateur dans la plage de dates spécifiée
         */

        /**
         * Récupère les activités d'un utilisateur qui ont brûlé plus qu'un certain nombre de calories.
         *
         * @param userId L'ID de l'utilisateur
         * @param minCalories Le nombre minimum de calories
         * @return Une liste des activités à haute dépense calorique
         */
        List<Activity> getHighCalorieActivities(Long userId, int minCalories);

        /**
         * Calcule la durée moyenne d'un type d'activité spécifique pour un utilisateur.
         *
         * @param userId L'ID de l'utilisateur
         * @param type Le type d'activité
         * @return La durée moyenne de l'activité
         */
        Double getAverageDurationForActivityType(Long userId, String type);

        /**
         * Récupère les activités qui ont brûlé le plus de calories pour un utilisateur.
         *
         * @param userId L'ID de l'utilisateur
         * @param limit Le nombre maximum d'activités à retourner
         * @return Une liste des activités qui ont brûlé le plus de calories
         */
        List<Activity> getTopCalorieBurningActivities(Long userId, int limit);

        /**
         * Récupère des statistiques agrégées sur les activités d'un utilisateur dans une plage de dates.
         *
         * @param userId L'ID de l'utilisateur
         * @param startDate La date de début de la plage
         * @param endDate La date de fin de la plage
         * @return Un objet ActivityStats contenant les statistiques
         */
        ActivityDto getActivityStatistics(Long userId, LocalDate startDate, LocalDate endDate);

        /**
         * Identifie les jours où l'utilisateur a été le plus actif.
         *
         * @param userId L'ID de l'utilisateur
         * @param limit Le nombre de jours à retourner
         * @return Une liste de tableaux d'objets contenant la date et le nombre d'activités
         */
        List<Object[]> getMostActiveDays(Long userId, int limit);

        /**
         * Identifie les types d'activités que l'utilisateur pratique régulièrement.
         *
         * @param userId L'ID de l'utilisateur
         * @param minOccurrences Le nombre minimum d'occurrences pour considérer une activité comme récurrente
         * @return Une liste de tableaux d'objets contenant le type d'activité et le nombre d'occurrences
         */
        List<Object[]> getRecurringActivities(Long userId, int minOccurrences);

        /**
         * Supprime une activité par son ID.
         *
         * @param id L'ID de l'activité à supprimer
         */
        void deleteActivity(Long id);

        /**
         * Met à jour une activité existante.
         *
         * @param id L'ID de l'activité à mettre à jour
         * @param activityDetails Les nouvelles données de l'activité
         * @return L'activité mise à jour
         */
        Activity updateActivity(Long id, Activity activityDetails);
    }

