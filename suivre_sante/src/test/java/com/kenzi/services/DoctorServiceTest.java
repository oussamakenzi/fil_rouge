package com.kenzi.services;

import com.kenzi.dtos.DoctorDto;
import com.kenzi.mapper.DoctorMapper;
import com.kenzi.models.Doctor;
import com.kenzi.repositories.DoctorRepository;
import com.kenzi.services.impl.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorMapper doctorMapper;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor doctor;
    private DoctorDto doctorDto;

    @BeforeEach
    public void setUp() {
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setEmail("doctor@example.com");
        doctorDto = new DoctorDto();
        doctorDto.setEmail("doctor@example.com");
    }

    @Test
    public void testGetAllDoctors() {
        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor));
        when(doctorMapper.doctorToDTO(doctor)).thenReturn(doctorDto);

        var result = doctorService.getAllDoctors();

        assertEquals(1, result.size());
        assertEquals(doctorDto.getEmail(), result.get(0).getEmail());
        verify(doctorRepository, times(1)).findAll();
        verify(doctorMapper, times(1)).doctorToDTO(doctor);
    }

    @Test
    public void testGetDoctorById() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(doctorMapper.doctorToDTO(doctor)).thenReturn(doctorDto);

        Optional<DoctorDto> result = doctorService.getDoctorById(1L);

        assertTrue(result.isPresent());
        assertEquals(doctorDto.getEmail(), result.get().getEmail());
    }

    @Test
    public void testSaveDoctor() {
        when(doctorMapper.dtoToDoctor(doctorDto)).thenReturn(doctor);
        when(doctorRepository.save(doctor)).thenReturn(doctor);
        when(doctorMapper.doctorToDTO(doctor)).thenReturn(doctorDto);

        DoctorDto result = doctorService.saveDoctor(doctorDto);

        assertEquals(doctorDto.getEmail(), result.getEmail());
        verify(doctorMapper, times(1)).dtoToDoctor(doctorDto);
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    public void testDeleteDoctor() {
        doNothing().when(doctorRepository).deleteById(1L);

        assertDoesNotThrow(() -> doctorService.deleteDoctor(1L));
        verify(doctorRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetDoctorByEmail() {
        when(doctorRepository.findByEmail("doctor@example.com")).thenReturn(Optional.of(doctor));
        when(doctorMapper.doctorToDTO(doctor)).thenReturn(doctorDto);

        Optional<DoctorDto> result = doctorService.getDoctorByEmail("doctor@example.com");

        assertTrue(result.isPresent());
        assertEquals(doctorDto.getEmail(), result.get().getEmail());
    }

    // Ajoutez d'autres tests pour les méthodes restantes...
}
