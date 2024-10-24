/*package com.kenzi.services;

import com.kenzi.dtos.AppointmentDto;
import com.kenzi.mapper.AppointmentMapper;
import com.kenzi.models.Appointment;
import com.kenzi.models.Patient;
import com.kenzi.repositories.AppointmentRepositoriy;
import com.kenzi.repositories.UserRepository;
import com.kenzi.services.impl.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private UserRepository patientRepository;

    @Mock
    private AppointmentMapper appointmentMapper;

    @Mock
    private AppointmentRepositoriy appointmentRepositoriy;

    @InjectMocks
    private AppointmentService appointmentService;

    private Patient patient;
    private Appointment appointment;
    private AppointmentDto appointmentDto;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setEmail("user@example.com");

        appointment = new Appointment();
        appointment.setId(1L);
        appointment.setPatient(patient);

        appointmentDto = new AppointmentDto();
        appointmentDto.setId(1L);
        appointmentDto.setUserId(1L);
    }

    @Test
    public void testSaveAppointment_Success() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(appointmentMapper.toEntity(appointmentDto)).thenReturn(appointment);
        when(appointmentRepositoriy.save(appointment)).thenReturn(appointment);
        when(appointmentMapper.toDto(appointment)).thenReturn(appointmentDto);

        AppointmentDto result = appointmentService.saveAppointment(appointmentDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getUserId());
        verify(patientRepository, times(1)).findById(1L);
        verify(appointmentMapper, times(1)).toEntity(appointmentDto);
        verify(appointmentRepositoriy, times(1)).save(appointment);
        verify(appointmentMapper, times(1)).toDto(appointment);
    }

    @Test
    public void testSaveAppointment_UserNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            appointmentService.saveAppointment(appointmentDto);
        });

        assertEquals("User not found", exception.getMessage());
        verify(patientRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(appointmentMapper, appointmentRepositoriy);
    }

    @Test
    public void testGetAppointmentsForUser_Success() {
        when(appointmentRepositoriy.findByUserId(1L)).thenReturn(Arrays.asList(appointment));
        when(appointmentMapper.toDto(appointment)).thenReturn(appointmentDto);

        List<AppointmentDto> result = appointmentService.getAppointmentsForUser(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(appointmentRepositoriy, times(1)).findByUserId(1L);
        verify(appointmentMapper, times(1)).toDto(appointment);
    }

    @Test
    public void testDeleteAppointment_Success() {
        doNothing().when(appointmentRepositoriy).deleteById(1L);

        assertDoesNotThrow(() -> appointmentService.deleteAppointment(1L));
        verify(appointmentRepositoriy, times(1)).deleteById(1L);
    }
}*/
