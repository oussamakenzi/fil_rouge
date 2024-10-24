package com.kenzi.services.impl;

import com.kenzi.models.Doctor;
import com.kenzi.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).get();
    }

}
