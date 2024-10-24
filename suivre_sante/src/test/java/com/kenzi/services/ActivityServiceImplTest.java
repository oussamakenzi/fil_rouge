/*package com.kenzi.services;

import com.kenzi.models.Activity;
import com.kenzi.models.Patient;
import com.kenzi.repositories.ActivityRepository;
import com.kenzi.repositories.UserRepository;
import com.kenzi.services.impl.ActivityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceImplTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private UserRepository patientRepository;

    @InjectMocks
    private ActivityServiceImpl activityService;

    private Patient patient;
    private Activity activity;
    private Activity activityDetails;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setEmail("user@example.com");

        activity = new Activity();
        activity.setId(1L);
        activity.setType("Running");
        activity.setStartTime(LocalDateTime.now().minusHours(2));
        activity.setEndTime(LocalDateTime.now());
        activity.setDuration(120); // minutes
        activity.setDistance(10.0); // km
        activity.setCalories(600);
        activity.setNotes("Morning run");
        activity.setPatient(patient);

        activityDetails = new Activity();
        activityDetails.setType("Cycling");
        activityDetails.setStartTime(LocalDateTime.now().minusHours(3));
        activityDetails.setEndTime(LocalDateTime.now().minusHours(1));
        activityDetails.setDuration(120); // minutes
        activityDetails.setDistance(20.0); // km
        activityDetails.setCalories(800);
        activityDetails.setNotes("Afternoon cycling");
    }

    @Test
    public void testSaveActivity_Success() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(activityRepository.save(activity)).thenReturn(activity);

        Activity result = activityService.saveActivity(activity);

        assertNotNull(result);
        assertEquals("Running", result.getType());
        assertEquals(patient, result.getPatient());
        verify(patientRepository, times(1)).findById(1L);
        verify(activityRepository, times(1)).save(activity);
    }

    @Test
    public void testSaveActivity_UserNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            activityService.saveActivity(activity);
        });

        assertEquals("User not found", exception.getMessage());
        verify(patientRepository, times(1)).findById(1L);
        verify(activityRepository, never()).save(any(Activity.class));
    }

    @Test
    public void testGetActivityById_Found() {
        when(activityRepository.findById(1L)).thenReturn(Optional.of(activity));

        Optional<Activity> result = activityService.getActivityById(1L);

        assertTrue(result.isPresent());
        assertEquals("Running", result.get().getType());
        verify(activityRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetActivityById_NotFound() {
        when(activityRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Activity> result = activityService.getActivityById(1L);

        assertFalse(result.isPresent());
        verify(activityRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllActivitiesByUserId() {
        when(activityRepository.findByUserId(1L)).thenReturn(Arrays.asList(activity));

        List<Activity> result = activityService.getAllActivitiesByUserId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Running", result.get(0).getType());
        verify(activityRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void testGetHighCalorieActivities() {
        int minCalories = 500;
        when(activityRepository.findHighCalorieActivities(1L, minCalories)).thenReturn(Arrays.asList(activity));

        List<Activity> result = activityService.getHighCalorieActivities(1L, minCalories);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getCalories() >= minCalories);
        verify(activityRepository, times(1)).findHighCalorieActivities(1L, minCalories);
    }

    @Test
    public void testGetAverageDurationForActivityType() {
        String type = "Running";
        Double averageDuration = 120.0;
        when(activityRepository.getAverageDurationByUserAndType(1L, type)).thenReturn(averageDuration);

        Double result = activityService.getAverageDurationForActivityType(1L, type);

        assertNotNull(result);
        assertEquals(averageDuration, result);
        verify(activityRepository, times(1)).getAverageDurationByUserAndType(1L, type);
    }

    @Test
    public void testGetTopCalorieBurningActivities() {
        int limit = 5;
        when(activityRepository.findTopCalorieBurningActivities(1L, limit)).thenReturn(Arrays.asList(activity));

        List<Activity> result = activityService.getTopCalorieBurningActivities(1L, limit);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Running", result.get(0).getType());
        verify(activityRepository, times(1)).findTopCalorieBurningActivities(1L, limit);
    }

    @Test
    public void testGetRecurringActivities() {
        int minOccurrences = 3;
        when(activityRepository.findRecurringActivities(1L, minOccurrences)).thenReturn(Arrays.asList());

        List<Object[]> result = activityService.getRecurringActivities(1L, minOccurrences);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(activityRepository, times(1)).findRecurringActivities(1L, minOccurrences);
    }

    @Test
    public void testDeleteActivity_Success() {
        doNothing().when(activityRepository).deleteById(1L);

        assertDoesNotThrow(() -> activityService.deleteActivity(1L));
        verify(activityRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateActivity_Success() {
        when(activityRepository.findById(1L)).thenReturn(Optional.of(activity));
        when(activityRepository.save(any(Activity.class))).thenReturn(activity);

        Activity updatedActivity = activityService.updateActivity(1L, activityDetails);

        assertNotNull(updatedActivity);
        assertEquals("Cycling", updatedActivity.getType());
        assertEquals(20.0, updatedActivity.getDistance());
        assertEquals(800, updatedActivity.getCalories());
        verify(activityRepository, times(1)).findById(1L);
        verify(activityRepository, times(1)).save(activity);
    }

    @Test
    public void testUpdateActivity_NotFound() {
        when(activityRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            activityService.updateActivity(1L, activityDetails);
        });

        assertEquals("Activity not found for this id :: 1", exception.getMessage());
        verify(activityRepository, times(1)).findById(1L);
        verify(activityRepository, never()).save(any(Activity.class));
    }
}

*/