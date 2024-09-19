package com.kenzi.services.impl;

import com.kenzi.dtos.DoctorDto;
import com.kenzi.mapper.DoctorMapper;
import com.kenzi.models.Doctor;
import com.kenzi.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::doctorToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DoctorDto> getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::doctorToDTO);
    }

    public DoctorDto saveDoctor(DoctorDto DoctorDto) {
        Doctor doctor = doctorMapper.dtoToDoctor(DoctorDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.doctorToDTO(savedDoctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public Optional<DoctorDto> getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email)
                .map(doctorMapper::doctorToDTO);
    }

    public List<DoctorDto> getDoctorsBySpecialite(String specialite) {
        return doctorRepository.findBySpecialite(specialite).stream()
                .map(doctorMapper::doctorToDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorDto> getDoctorsByHopital(String hopital) {
        return doctorRepository.findByHopital(hopital).stream()
                .map(doctorMapper::doctorToDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorDto> searchDoctorsByName(String name) {
        return doctorRepository.findByFirstNameOrLastNameContainingIgnoreCase(name).stream()
                .map(doctorMapper::doctorToDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorDto> getDoctorsBySpecialiteAndHopital(String specialite, String hopital) {
        return doctorRepository.findBySpecialiteAndHopital(specialite, hopital).stream()
                .map(doctorMapper::doctorToDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorDto> getDoctorsByExperience(String years) {
        return doctorRepository.findByExperienceGreaterThanEqual(years).stream()
                .map(doctorMapper::doctorToDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorDto> getTopDoctorsBySpecialite(String specialite) {
        return doctorRepository.findTopDoctorsBySpecialite(specialite).stream()
                .map(doctorMapper::doctorToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DoctorDto> getRandomDoctorBySpecialite(String specialite) {
        return doctorRepository.findRandomDoctorBySpecialite(specialite)
                .map(doctorMapper::doctorToDTO);
    }

    public long countDoctorsByHopital(String hopital) {
        return doctorRepository.countDoctorsByHopital(hopital);
    }

    public boolean existsByEmail(String email) {
        return doctorRepository.existsByEmail(email);
    }
}