package com.kenzi.services.impl;

import com.kenzi.models.Admin;
import com.kenzi.models.Patient;
import com.kenzi.models.User;
import com.kenzi.models.Doctor;
import com.kenzi.repositories.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Optional<User> getUserByEmail(String email) {
        return (userRepository.findByEmail(email));
    }

    public Optional<Patient> getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public Optional<Doctor> getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}